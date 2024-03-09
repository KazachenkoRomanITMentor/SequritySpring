package web.securityspring.controllers;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import web.securityspring.models.User;
import web.securityspring.services.UserService;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String login(){
        return "login";
    }

}
