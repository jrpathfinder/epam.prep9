package com.epam.learn.springdata.controller;

import com.epam.learn.springdata.facade.BookingFacade;
import com.epam.learn.springdata.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final BookingFacade facade;

    @GetMapping("/user/id")
    public String getUserById(Model model, @RequestParam("id") Integer id){
        Optional<User> user = facade.getUserById(id);
        model.addAttribute("user", user.orElseThrow());
        return "user.html";
    }

    @GetMapping("/user/email")
    public String getUserByEmail(Model model, @RequestParam("email") String email){
        User user = facade.getUserByEmail(email);
        model.addAttribute("user", user);
        return "user.html";
    }

    @GetMapping("/user/name")
    public String getUsersByName(Model model, @RequestParam("name") String name,
                                 @RequestParam("size") Optional<Integer> size,
                                 @RequestParam("num") Optional<Integer> num){
        final int currentNum = num.orElse(1);
        final int pageSize = size.orElse(5);
        List<User> users = facade.getUsersByName(name, pageSize, currentNum);
        model.addAttribute("users", users);
        return "users.html";
    }

    @GetMapping("/user/pdf")
    public ModelAndView getUsersByName(ModelAndView modelAndView, @RequestHeader(HttpHeaders.ACCEPT) String accept,
                                           @RequestParam("name") String name,
                                           @RequestParam("size") Optional<Integer> size,
                                           @RequestParam("num") Optional<Integer> num){
        final int currentNum = num.orElse(1);
        final int pageSize = size.orElse(5);
        List<User> users = facade.getUsersByName(name, pageSize, currentNum);
        return new ModelAndView("pdfView", "listUsers", users);
    }

    @PostMapping("/user")
    public void createUser(@ModelAttribute User user){
        facade.createUser(user);
    }

    @PutMapping("/user")
    public void updateUser(@ModelAttribute User user){
        facade.updateUser(user);
    }

    @DeleteMapping("/user")
    public void delete(@RequestParam("id") Integer id){
        facade.deleteUser(id);
    }
}
