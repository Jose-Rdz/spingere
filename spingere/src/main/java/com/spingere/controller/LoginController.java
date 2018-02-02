package com.spingere.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author G13380
 */
@Controller
@RequestMapping("/")
public class LoginController {

    @GetMapping
    public String main() {
        return "login";
    }
    
    @GetMapping("/login")
    public ModelAndView login(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout) {

        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "El usuario y/o contraseña proporcionados no son válidos.");
        }

        if (logout != null) {
            model.addObject("message", "Su sesión se ha cerrado correctamente...");
        }

        model.setViewName("login");
        return model;
    }
    
}
