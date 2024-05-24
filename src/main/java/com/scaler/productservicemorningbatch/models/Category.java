package com.scaler.productservicemorningbatch.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Category extends BaseModel{
  /*  @Id
    private long id; */ //Moved to BaseModel
    private String title;
}
