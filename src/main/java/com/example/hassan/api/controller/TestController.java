package com.example.hassan.api.controller;

//import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Conditional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@Api(tags = "API", value = "")
@RequestMapping("/public")
public class TestController {

    @Value("${my.value.myName}")
    private String myValue;

    @GetMapping
    public String hello() {
        return "Hello World "+ myValue;
    }

}
