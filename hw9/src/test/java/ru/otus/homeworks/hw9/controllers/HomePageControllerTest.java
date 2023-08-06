package ru.otus.homeworks.hw9.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(HomePageController.class)
@DisplayName("Контроллер главной страницы должен")
public class HomePageControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @DisplayName("перенаправлять на страницу с книгами")
    void shouldRedirectToBookListPage() throws Exception {
        mvc.perform(get("/"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/book"));
    }

}
