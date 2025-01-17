package com.scaler.productservicemorningbatch.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseModel {
   /* @Id
    private long id;*/
    private String title;
    private double price;
    @ManyToOne(cascade = {CascadeType.ALL})
    private Category category;
    private String description;
    private String image;
}
