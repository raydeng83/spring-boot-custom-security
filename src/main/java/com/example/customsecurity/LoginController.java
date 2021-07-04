package com.example.customsecurity;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
class LoginController {

    @RequestMapping("/login")
    public ModelAndView login(@RequestParam Optional<String> error){
        ModelAndView modelAndView = new ModelAndView("login/login");
        if(error.isPresent()){
            modelAndView.addObject("error", "Invalid Credentials");
        }
        return modelAndView;
    }

}
