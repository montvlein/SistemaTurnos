package com.dh.proyecto.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class vistaController {

    @GetMapping
    public String renderIndex() {
        return "index";
    }

    @GetMapping("user")
    public String renderUser() {
        return "user";
    }

    @GetMapping("admin")
    public String renderAdmin() {
        return "admin";
    }
}
