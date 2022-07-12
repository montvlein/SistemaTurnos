package com.dh.proyecto.Controller;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping
public class vistaController {

    private static final Logger logger = Logger.getLogger(vistaController.class);

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
