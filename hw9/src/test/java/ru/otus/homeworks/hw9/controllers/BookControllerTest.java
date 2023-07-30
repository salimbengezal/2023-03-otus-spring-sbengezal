package ru.otus.homeworks.hw9.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.homeworks.hw9.dto.NewBookDtoRequest;
import ru.otus.homeworks.hw9.dto.UpdateBookDtoRequest;
import ru.otus.homeworks.hw9.service.AuthorService;
import ru.otus.homeworks.hw9.service.BookService;
import ru.otus.homeworks.hw9.service.GenreService;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
@DisplayName("Контроллер с действиями книг должен ")
public class BookControllerTest {

    @MockBean
    private BookService bookService;

    @MockBean
    private AuthorService authorService;

    @MockBean
    private GenreService genreService;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    @DisplayName("вызывать сервис для создания книги")
    void shouldCreateBook() throws Exception {
        val newBook = new NewBookDtoRequest("new-book", (short) 123, "some-author-id",
                "some-genre-author-id");
        mvc.perform(post("/book")
                        .param("create", "")
                        .param("name", newBook.getName())
                        .param("releaseYear", newBook.getReleaseYear().toString())
                        .param("authorId", newBook.getAuthorId())
                        .param("genreId", newBook.getGenreId())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string(HttpHeaders.LOCATION, "/book"));
        verify(bookService, times(1)).add(newBook);
        verify(authorService, never()).getAll();
        verify(genreService, never()).getAll();
    }

    @Test
    @DisplayName("вызывать сервис для удаления книги")
    void shouldDeleteBook() throws Exception {
        val id = "some-id";
        mvc.perform(post("/book")
                        .param("delete", "")
                        .param("id", id))
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
        mvc.perform(post("/book")
                        .param("id", updatedBook.id())
                        .param("update", "")
                        .param("name", updatedBook.name())
                        .param("releaseYear", updatedBook.releaseYear().toString())
                        .param("authorId", updatedBook.authorId())
                        .param("genreId", updatedBook.genreId())
                        .content(requestBody))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string(HttpHeaders.LOCATION, "/book"));
        verify(bookService, times(1)).update(updatedBook);
        verify(authorService, never()).getAll();
        verify(genreService, never()).getAll();
    }

    @Test
    @DisplayName("проверять на валидность новый комментарий")
    void shouldValidateCreatingOfBook() throws Exception {
        val book = new NewBookDtoRequest("", (short) -1, "some-author-id", "some-genre-author-id");
        mvc.perform(post("/book")
                        .param("create", "")
                        .param("name", book.getName())
                        .param("releaseYear", book.getReleaseYear().toString())
                        .param("authorId", book.getAuthorId())
                        .param("genreId", book.getGenreId())
                )
                .andExpect(status().isOk())
                .andExpect(model().hasErrors())
                .andExpect(model().errorCount(3))
                .andExpect(view().name("book/new"));
    }

    @Test
    @DisplayName("проверять на валидность новый комментарий")
    void shouldValidateUpdatingOfBook() throws Exception {
        val book = new UpdateBookDtoRequest("", "", (short) -1, "some-author-id", "some-genre-author-id");
        mvc.perform(post("/book")
                        .param("update", "")
                        .param("name", book.name())
                        .param("releaseYear", book.releaseYear().toString())
                        .param("authorId", book.authorId())
                        .param("genreId", book.genreId())
                )
                .andExpect(status().isOk())
                .andExpect(model().hasErrors())
                .andExpect(model().errorCount(4))
                .andExpect(view().name("book/edit"));
    }

}
