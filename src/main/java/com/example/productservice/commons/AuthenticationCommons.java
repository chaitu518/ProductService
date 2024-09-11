package com.example.productservice.commons;

import com.example.productservice.dtos.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthenticationCommons {
    private RestTemplate restTemplate;
    public AuthenticationCommons(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public UserDto validateToken(String token) {
        ResponseEntity<UserDto> response =restTemplate.getForEntity("http://userservice-env.eu-north-1.elasticbeanstalk.com/users/validate/"+token, UserDto.class);
        if(response.getBody()==null){
            throw new RuntimeException("Invalid Token");
        }
        return response.getBody();
    }
}
