package ru.otus.homeworks.hw10.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.homeworks.hw10.dto.*;
import ru.otus.homeworks.hw10.service.BookService;

import java.util.stream.IntStream;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
@DisplayName("Контроллер с книгами должен ")
public class BookControllerTest {

    @MockBean
    private BookService bookService;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    @DisplayName("возвращать книги")
    void shouldReturnBookList() throws Exception {
        val books = IntStream.rangeClosed(1, 2).mapToObj(number -> {
            val author = new AuthorDtoResponse("a_id" + number, "a_name" + number);
            val genre = new GenreDtoResponse("g_id" + number, "g_name" + number);
            return new BookDtoResponse("b_id" + number, "b_name" + number, (short) 123, author, genre);
        }).toList();
        val bookJson = mapper.writeValueAsString(books);
        when(bookService.getAll()).thenReturn(books);
        mvc.perform(get("/api/book"))
                .andExpect(status().isOk())
                .andExpect(content().json(bookJson));
        verify(bookService, times(1)).getAll();
    }

    @Test
    @DisplayName("создавать книгу")
    void shouldCreateBook() throws Exception {
        val book = new NewBookDtoRequest("name", (short) 123, "a_id", "g_id");
        val bookJson = mapper.writeValueAsString(book);
        mvc.perform(post("/api/book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookJson))
                .andExpect(status().isOk());
        verify(bookService, times(1)).add(book);
    }

    @Test
    @DisplayName("удалять книгу")
    void shouldDeleteBook() throws Exception {
        val id = "some-id";
        mvc.perform(delete("/api/book/%s".formatted(id)))
                .andExpect(status().isOk());
        verify(bookService, times(1)).deleteById(id);
    }

    @Test
    @DisplayName("изменять книгу")
    void shouldUpdateBook() throws Exception {
        val book = new UpdateBookDtoRequest("b_id", "new-name", (short) 123, "a_id", "g_id");
        val bookJson = mapper.writeValueAsString(book);
        mvc.perform(patch("/api/book/%s".formatted(book.id()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookJson))
                .andExpect(status().isOk());
        verify(bookService, times(1)).update(book);
    }

    @Test
    @DisplayName("проверять на валидность новый комментарий")
    void shouldValidateCreatingOfBook() throws Exception {
        val book = new NewBookDtoRequest("", (short) -1, "a_id", "g_id");
        val bookJson = mapper.writeValueAsString(book);
        mvc.perform(post("/api/book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookJson))
                .andExpect(status().isBadRequest());
        verify(bookService, never()).add(book);
    }

    @Test
    @DisplayName("проверять на валидность обновляемый комментарий")
    void shouldValidateUpdatingOfBook() throws Exception {
        val book = new UpdateBookDtoRequest("2", "", (short) -1, "a_id", "g_id");
        val bookJson = mapper.writeValueAsString(book);
        mvc.perform(patch("/api/book/%s".formatted(book.id()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookJson))
                .andExpect(status().isBadRequest());
        verify(bookService, never()).update(book);
    }

}
