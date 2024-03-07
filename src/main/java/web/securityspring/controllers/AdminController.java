package web.securityspring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import web.securityspring.models.Role;
import web.securityspring.models.User;
import web.securityspring.services.RoleService;
import web.securityspring.services.UserService;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("")
    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String GetUserListByAdmin(Model model){
        model.addAttribute("users", userService.getAllUser());
        return "adminPage";
    }



    @GetMapping("/adduser")
    public String showAddUserForm(@ModelAttribute("user") User user, Model model){
        model.addAttribute("roles", roleService.getAllRoles());
        return "adduser";
    }

    @PostMapping("/adduser")
    public String createUser(@Validated(User.class) @ModelAttribute("user") User user){
        userService.addUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/edit/{id}")
    public String showUserUpdateForm(@PathVariable("id") long id, Model model){
        User readableUser =userService.findUserById(id);
        model.addAttribute("roles", roleService.getAllRoles());
        model.addAttribute("user", readableUser);
        return"aditUser";

    }

    @PostMapping("/edit/{id}")
    public String updateUser(@PathVariable("id") long id, User user,
                             BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            user.setId(id);
            return "aditUser";
        }else {
        userService.addUser(user);
        return "redirect:/admin";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteUser (@PathVariable("id") long id, Model model){
        userService.deleteUserById(id);
        return "redirect:/admin";
    }



}
