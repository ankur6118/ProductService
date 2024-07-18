package com.scaler.productservicemorningbatch.services;

import com.scaler.productservicemorningbatch.dtos.FakeStoreProductDto;
import com.scaler.productservicemorningbatch.exceptions.InvalidProductIdException;
import com.scaler.productservicemorningbatch.models.Category;
import com.scaler.productservicemorningbatch.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Objects;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{

    RestTemplate restTemplate;

    RedisTemplate redisTemplate;

    private String baseUrl = "https://fakestoreapi.com/products/";

    FakeStoreProductService(RestTemplate restTemplate, RedisTemplate redisTemplate){
        this.restTemplate = restTemplate;
        this.redisTemplate = redisTemplate;
    }

    private Product convertFakeStoreProductDtoToProduct(FakeStoreProductDto fakeStoreProductDto){
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setImage(fakeStoreProductDto.getImage());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setDescription(fakeStoreProductDto.getDescription());
        Category category = new Category();
        category.setTitle(fakeStoreProductDto.getCategory());
        product.setCategory(category);
        return product;
    }

    @Override
    public Product getProductById(Long id) throws InvalidProductIdException {

        Product product = (Product) redisTemplate.opsForHash().get("PRODUCTS","PRODUCT_"+id);

        if(product != null)
            return product;


        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject(baseUrl + id, FakeStoreProductDto.class);

        if(fakeStoreProductDto ==null)
            throw new InvalidProductIdException(id,"Invalid Product Id");

        product = convertFakeStoreProductDtoToProduct(fakeStoreProductDto);
        redisTemplate.opsForHash().put("PRODUCTS","PRODUCT_"+id, product);

        return product;
    }

    @Override
    public Page<Product> getAllProduct(int pageNumber, int pageSize,
                                       String sortDir) {
        FakeStoreProductDto[] response = restTemplate.getForObject(baseUrl, FakeStoreProductDto[].class);

        if(response == null)
            return null;
        /*
        List<Product> products = new ArrayList<>();
        for(FakeStoreProductDto fakeStoreProductDto : response)
            products.add(convertFakeStoreProductDtoToProduct(fakeStoreProductDto));

        return products;
        */
        //using Stream
        return (Page<Product>) Arrays.stream(response).map(this::convertFakeStoreProductDtoToProduct).toList();
    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        //Converting the incoming Json as per fakeStoreProduct
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setImage(product.getImage());

        RequestCallback requestCallback = restTemplate.httpEntityCallback(fakeStoreProductDto, FakeStoreProductDto.class);
        HttpMessageConverterExtractor<FakeStoreProductDto> responseExtractor = new HttpMessageConverterExtractor<>(FakeStoreProductDto.class, restTemplate.getMessageConverters());
        FakeStoreProductDto response = restTemplate.execute(baseUrl + id, HttpMethod.PUT, requestCallback, responseExtractor);

        if(response ==null)
            return null;

        return convertFakeStoreProductDtoToProduct(response);
    }


    @Override
    public Product updateProduct(Long id, Product product) {

        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setPrice(product.getPrice());

        /*RequestCallback requestCallback = restTemplate.httpEntityCallback(fakeStoreProductDto, FakeStoreProductDto.class);
        HttpMessageConverterExtractor<FakeStoreProductDto> responseExtractor = new HttpMessageConverterExtractor<>(FakeStoreProductDto.class, restTemplate.getMessageConverters());
        FakeStoreProductDto response = restTemplate.execute(baseUrl + id, HttpMethod.PATCH, requestCallback, responseExtractor);

        if(response ==null)
            return null;

        return convertFakeStoreProductDtoToProduct(response);*/


        HttpEntity<FakeStoreProductDto> requestEntity = new HttpEntity<>(fakeStoreProductDto);
        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.exchange(baseUrl + "/" + id, HttpMethod.PATCH, requestEntity, FakeStoreProductDto.class);

        return convertFakeStoreProductDtoToProduct(Objects.requireNonNull(responseEntity.getBody()));
    }

    /*@Override
    public Map<String, Object> updateProduct(Long id, Map<String, Object> fields) {

        Map<String,Object> updateProduct = restTemplate.patchForObject(baseUrl + "/" + id,fields , Map.class);
        System.out.println(updateProduct);
        return updateProduct;
    }*/

    @Override
    public void deleteProduct(Long id) {

    }
}

