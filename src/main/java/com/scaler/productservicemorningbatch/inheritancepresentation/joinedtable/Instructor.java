package com.scaler.productservicemorningbatch.inheritancepresentation.joinedtable;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="jt_instructor")
public class Instructor extends User {
    private String specialization;
}
