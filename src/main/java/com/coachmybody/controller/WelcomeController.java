package com.coachmybody.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Test")
@RestController
public class WelcomeController {

    @ApiOperation("Test API")
    @GetMapping("/test")
    public String welcome() {
        return "hello world";
    }
}
