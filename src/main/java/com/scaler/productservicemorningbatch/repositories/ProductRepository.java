package com.scaler.productservicemorningbatch.repositories;

import com.scaler.productservicemorningbatch.models.Category;
import com.scaler.productservicemorningbatch.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findById(Long id);

    Optional<Product> findByTitleAndDescription(String title, String description); //And Operator

    List<Product> findByTitleContaining(String word); // Like Operator

    List<Product> findTopThreeByTitle(String title); //limit the result upto 3

    Optional<Product> findByCategory(Category category);

    void deleteById(Long id);

    void deleteByTitle(String title);

    Product save(Product product);

    //HQL Queries

/*    @Query("Select p from Product p where p.id>100 and lower(p.title) like '%with%'") //HQL -> Hibernate Query Language
    List<Product> someRandomQuery();*/

    /*@Query("Select p.id as id,p.title as title from Product p where p.id>100 and lower(p.title) like '%with%'")
    List<ProductWithIdAndTitle> someRandomQuery();*/

    /*@Query("Select p.id as id,p.title as title from Product p where p.id= :id")
    List<ProductWithIdAndTitle> someRandomQuery(@Param("id") Long id);*/

    //SQL Queries

    @Query(value = "Select * from product where id = ?1", nativeQuery = true)
    List<Product> someRandomQuery(Long id);
}
