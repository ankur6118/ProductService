package com.scaler.productservicemorningbatch.services;

import com.scaler.productservicemorningbatch.models.Product;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ProductService {
    public Product getProductById(Long Id);
    public List<Product> getAllProduct();
    public Product createProduct(Product product);
    public Product replaceProduct(Long id, Product product);
    public Product updateProduct(Long id, Product product);
    public void deleteProduct(Long id);

}
