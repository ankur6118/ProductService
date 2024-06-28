package com.scaler.productservicemorningbatch.controllers;

import com.scaler.productservicemorningbatch.exceptions.InvalidProductIdException;
import com.scaler.productservicemorningbatch.models.Product;
import com.scaler.productservicemorningbatch.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductControllerTest {

    @Autowired
    private ProductController productController;

    @MockBean
    private ProductService productService;

    @Test
    void testGetProductByIdValidCase() throws InvalidProductIdException {

        //productController.getProductById(10L);

        Product product = new Product();
        product.setId(10L);
        product.setTitle("Celio jeans");
        product.setPrice(35000);

        when(productService.getProductById(10L))
                .thenReturn(product);

        ResponseEntity<Product> expectedProduct = productController.getProductById(10L);

        assertEquals(product, expectedProduct.getBody());

        assertEquals(HttpStatus.OK, expectedProduct.getStatusCode());
    }

    @Test
    void testGetProductByIdInValidCase() throws InvalidProductIdException {

        when(productController.getProductById(2000L))
                .thenThrow(new InvalidProductIdException(2000L, "Id not found"));

        assertThrows(InvalidProductIdException.class,
                ()-> productController.getProductById(2000L));
    }

    /*@Test
    void getAllProduct() {

        Product p1 = new Product();
        p1.setId(10L);
        p1.setTitle("Iphone 15");
        p1.setDescription("256 GB Iphone");
        p1.setPrice(1000);

        Product p2 = new Product();
        p2.setId(20L);
        p2.setTitle("Iphone 15");
        p2.setDescription("256 GB Iphone");
        p2.setPrice(1000);

        Product p3 = new Product();
        p3.setId(30L);
        p3.setTitle("Iphone 15");
        p3.setDescription("256 GB Iphone");
        p3.setPrice(1000);

        List<Product> productList = new ArrayList<>();

        productList.add(p1);
        productList.add(p2);
        productList.add(p3);

        when(productController.getAllProduct())
                .thenReturn(productList);

        assertEquals(productList,productController.getAllProduct());

    }*/
}