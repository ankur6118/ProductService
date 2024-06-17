package com.scaler.productservicemorningbatch.services;

import com.scaler.productservicemorningbatch.exceptions.InvalidProductIdException;
import com.scaler.productservicemorningbatch.models.Category;
import com.scaler.productservicemorningbatch.models.Product;
import com.scaler.productservicemorningbatch.repositories.CategoryRepository;
import com.scaler.productservicemorningbatch.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("selfProductService")
public class SelfProductService implements ProductService{

    ProductRepository productRepository;
    CategoryRepository categoryRepository;

    SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }
    @Override
    public Product getProductById(Long id) throws InvalidProductIdException {
        Optional<Product> optionalProduct= productRepository.findById(id);
        //throw  new InvalidProductIdException(id, "Invalid Id");
        return optionalProduct.orElse(null);
    }

    @Override
    public List<Product> getAllProduct() {
        return null;
    }

    @Override
    public Product createProduct(Product product) {
        //as we are using cascading
        /*Category category = product.getCategory();
        if(category.getId()==null){
            Category savedCategory = categoryRepository.save(category);
            product.setCategory(savedCategory);
        }*/
        return productRepository.save(product);
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        return null;
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        Optional<Product> optionalProduct= productRepository.findById(id);
        if(optionalProduct.isEmpty()) throw new RuntimeException("Invlid Product Id");
        Product currentProdduct = optionalProduct.get();
        if(product != null){
            if(product.getTitle() != null)
                currentProdduct.setTitle(product.getTitle());
            if(product.getImage() !=null)
                currentProdduct.setImage(product.getImage());
            if(product.getDescription()!=null)
                currentProdduct.setDescription(product.getDescription());
            return productRepository.save(currentProdduct);
        }
        return null;
    }

    @Override
    public void deleteProduct(Long id) {

    }
}
