package com.znzz.springboot_security.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestControllter {

    @GetMapping("/hello")
     public String hello(){
         return "hello";
     }


    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @GetMapping("/update")
    @Secured({"ROLE_sale","ROLE_manager"})
    public String update(){
        return "hello  update";
    }

}
