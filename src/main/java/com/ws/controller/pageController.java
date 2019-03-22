package com.ws.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class pageController {



    @RequestMapping("test")
    public  String test (){
        return "ossdome";
    }

    @RequestMapping("index")
    public  String index (){

        return "index";
    }




    @RequestMapping("showcoulist")
    public  String showcoulist (){

        return "showcoulist";
    }
}
