package com.sears.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sears.models.Person;
import com.sears.services.MainServices;

import org.springframework.ui.Model;

import java.util.List;

@Controller
public class MainController {

    @GetMapping("/hello")
    @ResponseBody
    public String sayHello() {
        System.out.println("*** sayHello ***" );

        return "Hello, World!";
    }

    @GetMapping("/home")
    public String home(Model model) {
        System.out.println("*** home ***" );
        
        MainServices service = new MainServices();
        
        List<Person> persons = service.allProducts();
        
        
        model.addAttribute("persons", persons);

        return "index";
    }
    

    

}
