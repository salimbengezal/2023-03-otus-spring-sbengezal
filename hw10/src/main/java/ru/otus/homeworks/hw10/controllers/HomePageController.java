package ru.otus.homeworks.hw10.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {

    @GetMapping("/")
    public String homePage() {
        return "redirect:/book";
    }
}
