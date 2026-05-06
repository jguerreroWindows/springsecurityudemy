package com.semana3java.springjwt.Security.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hola")
    public String index(){
        System.out.println("Hola");
        return "Hola contorller";
    }

}
