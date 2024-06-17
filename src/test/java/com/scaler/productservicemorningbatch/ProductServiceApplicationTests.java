package com.scaler.productservicemorningbatch;

import com.scaler.productservicemorningbatch.models.Category;
import com.scaler.productservicemorningbatch.models.Product;
import com.scaler.productservicemorningbatch.repositories.CategoryRepository;
import com.scaler.productservicemorningbatch.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class ProductServiceApplicationTests {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Test
    void contextLoads() {
    }

    @Test
    public void testQueries(){

        List<Product> products = productRepository.someRandomQuery(102L);



        for(Product productWithIdAndTitle : products){
            System.out.println(productWithIdAndTitle.getId());
            System.out.println(productWithIdAndTitle.getTitle());

        }

        System.out.println("DEBUG");

        Optional<Category> category = categoryRepository.findById(2L);


        if(category.isPresent()) {
            List<Product> optionalProduct = category.get().getProducts(); //Lazy Fetching Error will occur here as Product collection is not fetched by default.
            if (!optionalProduct.isEmpty()) {
                for (Product product : optionalProduct) {
                    System.out.println(product.getTitle());
                }
            } else {
                Optional<Product> pr =  productRepository.findByCategory(category.get());
                System.out.println("Lazy Fetching");
            }
        }


    }


}
