package com.scaler.productservicemorningbatch.services;

import com.scaler.productservicemorningbatch.exceptions.InvalidProductIdException;
import com.scaler.productservicemorningbatch.models.Product;

import java.util.List;

public interface ProductService {
    public Product getProductById(Long Id) throws InvalidProductIdException;
    public List<Product> getAllProduct();
    public Product createProduct(Product product);
    public Product replaceProduct(Long id, Product product);
    public Product updateProduct(Long id, Product product);
    //public Map<String, Object> updateProduct(Long id, Map<String, Object> fields);
    public void deleteProduct(Long id);

}
