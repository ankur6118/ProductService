package com.scaler.productservicemorningbatch;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//This controller supports Rest API's (HTTP Request)
//Request coming to /hello will be handle by this controller
@RestController
@RequestMapping("/say")
public class SampleAPIController {

    @GetMapping("/hello/{name}/{city}")
    public String sayHello(@PathVariable("name") String name, @PathVariable("city") String city){
        return "Hello!!" + " " + name + " " + city;
    }

}
