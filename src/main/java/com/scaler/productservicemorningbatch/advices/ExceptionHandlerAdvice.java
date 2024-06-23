package com.scaler.productservicemorningbatch.advices;

import com.scaler.productservicemorningbatch.dtos.ArithmeticExceptionDto;
import com.scaler.productservicemorningbatch.dtos.ArrayIndexOutOfBoundExceptionDto;
import com.scaler.productservicemorningbatch.dtos.InvalidProductIdExceptionDto;
import com.scaler.productservicemorningbatch.exceptions.InvalidProductIdException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<ArithmeticExceptionDto> handleArithmeticException(){
        ArithmeticExceptionDto dto = new ArithmeticExceptionDto();
        dto.setMessage("something went wrong");
        dto.setDetails("something went wrong random details");
        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ArrayIndexOutOfBoundsException.class)
    public ResponseEntity<ArrayIndexOutOfBoundExceptionDto> handleArrayIndexOutOfBoundException(){
        return null;
    }

    @ExceptionHandler(InvalidProductIdException.class)
    public ResponseEntity<InvalidProductIdExceptionDto> handleInvalidProductIdException(InvalidProductIdException e){
        InvalidProductIdExceptionDto dto = new InvalidProductIdExceptionDto();
        dto.setId(e.getProductId());
        dto.setMessage("Invalid Product Id is passed - Message from Controller Advices");
        dto.setDetails(e.getMessage());
        return new ResponseEntity<>(dto,HttpStatus.BAD_REQUEST);
    }
}
