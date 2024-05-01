package com.scaler.productservicemorningbatch.exceptions;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductControllerSpecificException extends Exception{
    private String message;
    private String details;

    public ProductControllerSpecificException(){
    }
}
