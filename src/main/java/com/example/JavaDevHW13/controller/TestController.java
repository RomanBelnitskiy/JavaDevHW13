package com.example.JavaDevHW13.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/test")
public class TestController {
    @GetMapping
    public ModelAndView sayHello() {
        ModelAndView mav = new ModelAndView("hello");
        return mav;
    }
}
