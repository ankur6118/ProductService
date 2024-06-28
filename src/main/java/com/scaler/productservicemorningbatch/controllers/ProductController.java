package com.scaler.productservicemorningbatch.controllers;

import com.scaler.productservicemorningbatch.commons.AuthenticationCommons;
import com.scaler.productservicemorningbatch.dtos.UserDto;
import com.scaler.productservicemorningbatch.exceptions.InvalidProductIdException;
import com.scaler.productservicemorningbatch.exceptions.ProductControllerSpecificException;
import com.scaler.productservicemorningbatch.models.Product;
import com.scaler.productservicemorningbatch.services.ProductService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    ProductService productService;

    AuthenticationCommons authenticationCommons;

    ProductController(@Qualifier("selfProductService") ProductService productService, AuthenticationCommons authenticationCommons){
        this.productService = productService;
        this.authenticationCommons = authenticationCommons;
    }

    //localhost:8080/products/30
    @SneakyThrows
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) throws InvalidProductIdException {
        Product product;

        //int i = 1/0;
        //throw new ProductControllerSpecificException();
//        try {
            product =  productService.getProductById(id);
//        }catch(RuntimeException e){
//            System.out.println("Something went wrong here");
//            return new ResponseEntity<>(product, HttpStatus.NOT_FOUND);
//        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/users/{token}")
    public ResponseEntity<List<Product>> getAllProduct(@PathVariable String token){
        UserDto userDto = authenticationCommons.validateToken(token);


        if(userDto == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        /*boolean isAdmin = false;
        for(Role role : userDto.getRoles()){
            if(role.equals("ADMIN")){
                isAdmin = true;
                break;
            }
        }

        if(!isAdmin){
            //throw some exception
            return null;
        }*/

        List<Product> products =  productService.getAllProduct();

        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product){
        return productService.createProduct(product);
    }

    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id") Long id, @RequestBody Product product){
        return productService.replaceProduct(id, product);
    }

    @PatchMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody Product product){
        return productService.updateProduct(id, product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long id){

    }


    //ProductControllerSpecificException
    @ExceptionHandler(ProductControllerSpecificException.class)
    public ResponseEntity<ProductControllerSpecificException> handleProductControllerSpecificException(){
        ProductControllerSpecificException pcse = new ProductControllerSpecificException();
        pcse.setMessage("Exception handled by Product Specific Controller");
        pcse.setDetails("Exception handled by Product Specific Controller Exception Handler");
        return new ResponseEntity<>(pcse,HttpStatus.BAD_REQUEST);
    }
}
