package com.scaler.productservicemorningbatch.services;

import com.scaler.productservicemorningbatch.dtos.FakeStoreProductDto;
import com.scaler.productservicemorningbatch.models.Product;

import java.util.List;

public interface ProductService {
    public Product getProductById(Long Id);
    public List<Product> getAllProduct();
    public Product createProduct(Product product);
    public Product replaceProduct(Long id, Product product);
    public Product updateProduct(Long id, Product product);
    public void deleteProduct(Long id);

}
