package com.scaler.productservicemorningbatch.commons;

import com.scaler.productservicemorningbatch.dtos.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthenticationCommons {

    private RestTemplate restTemplate;

    public AuthenticationCommons(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }
    public UserDto validateToken(String token){
        ResponseEntity<UserDto> response = restTemplate.postForEntity("http://localhost:3020/users/validate/" + token,
                null,
                UserDto.class);

        if(response.getBody()==null)
            return null;

        return response.getBody();
    }
}
