package com.scaler.productservicemorningbatch.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvalidProductIdExceptionDto extends ExceptionDto{
    private Long id;
}
