package com.scaler.productservicemorningbatch.inheritancepresentation.joinedtable;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "jt_ta")
public class TA extends User {
    private int noOfSessions;
    private double avgRating;
}
