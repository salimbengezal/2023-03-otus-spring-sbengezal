package ru.otus.homeworks.hw9.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.homeworks.hw9.dto.NewBookDtoRequest;
import ru.otus.homeworks.hw9.dto.UpdateBookDtoRequest;
import ru.otus.homeworks.hw9.service.BookService;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
@DisplayName("Контроллер с действиями книг должен ")
public class BookControllerTest {

    @MockBean
    private BookService bookService;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    @DisplayName("вызывать сервис для создания книги")
    void shouldCreateBook() throws Exception {
        val newBook = new NewBookDtoRequest("new-book", (short) 123, "some-author-id",
                "some-genre-author-id");
        val requestBody = mapper.writeValueAsString(newBook);
        mvc.perform(post("/book")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string(HttpHeaders.LOCATION, "/book"));
        verify(bookService, times(1)).add(newBook);
    }

    @Test
    @DisplayName("вызывать сервис для удаления книги")
    void shouldDeleteBook() throws Exception {
        val id = "some-id";
        mvc.perform(get("/book/delete").param("id", id))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string(HttpHeaders.LOCATION, "/book"));
        verify(bookService, times(1)).deleteById(id);
    }

    @Test
    @DisplayName("вызывать сервис для изменения книги")
    void shouldUpdateBook() throws Exception {
        val updatedBook = new UpdateBookDtoRequest("some-id", "new-book", (short) 123,
                "some-author-id", "some-genre-author-id");
        val requestBody = mapper.writeValueAsString(updatedBook);
        mvc.perform(post("/book/update")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string(HttpHeaders.LOCATION, "/book"));
        verify(bookService, times(1)).update(updatedBook);
    }

}
