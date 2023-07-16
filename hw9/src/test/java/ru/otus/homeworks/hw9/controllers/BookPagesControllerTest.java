package ru.otus.homeworks.hw9.controllers;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.homeworks.hw9.dto.AuthorDtoResponse;
import ru.otus.homeworks.hw9.dto.BookDtoResponse;
import ru.otus.homeworks.hw9.dto.CommentDtoResponse;
import ru.otus.homeworks.hw9.dto.GenreDtoResponse;
import ru.otus.homeworks.hw9.service.AuthorService;
import ru.otus.homeworks.hw9.service.BookService;
import ru.otus.homeworks.hw9.service.CommentService;
import ru.otus.homeworks.hw9.service.GenreService;

import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookPagesController.class)
@DisplayName("Контроллер с веб-страницами книг должен ")
public class BookPagesControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookService bookService;

    @MockBean
    private AuthorService authorService;

    @MockBean
    private GenreService genreService;

    @MockBean
    private CommentService commentService;

    @Test
    @DisplayName("отдавать страницу списка книг")
    void shouldReturnToBookListPage() throws Exception {
        val books = IntStream.rangeClosed(1, 2).mapToObj(n -> {
            val author = new AuthorDtoResponse("some-id" + n, "some-name" + n);
            val genre = new GenreDtoResponse("some-id" + n, "some-genre" + n);
            return new BookDtoResponse("some-id" + n, "some-name" + n, (short) 123, author, genre);
        }).toList();
        when(bookService.getAll()).thenReturn(books);
        mvc.perform(get("/book"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("books", books))
                .andExpect(view().name("book/list"));
        verify(bookService, times(1)).getAll();
    }

    @Test
    @DisplayName("отдавать страницу для создания новой книги")
    void shouldReturnToBookCreatePage() throws Exception {
        val genres = IntStream.rangeClosed(1, 4)
                .mapToObj(n -> new GenreDtoResponse(String.valueOf(n), "g" + n))
                .toList();
        val authors = IntStream.rangeClosed(1, 5)
                .mapToObj(n -> new AuthorDtoResponse(String.valueOf(n), "a" + n))
                .toList();
        when(authorService.getAll()).thenReturn(authors);
        when(genreService.getAll()).thenReturn(genres);
        mvc.perform(get("/book/new"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("authors", authors))
                .andExpect(model().attribute("genres", genres))
                .andExpect(view().name("book/new"));
        verify(authorService, times(1)).getAll();
        verify(genreService, times(1)).getAll();
    }

    @Test
    @DisplayName("отдавать страницу для просмотра книги")
    void shouldReturnToBookDetailsPage() throws Exception {
        val author = new AuthorDtoResponse("some-id", "some-name");
        val genre = new GenreDtoResponse("some-id", "some-genre");
        val book = new BookDtoResponse("1", "some-name", (short) 123, author, genre);
        List<CommentDtoResponse> comments = Collections.emptyList();
        when(bookService.getById(book.id())).thenReturn(book);
        when(commentService.getCommentsByBookId(book.id())).thenReturn(comments);
        mvc.perform(get("/book/" + book.id()))
                .andExpect(status().isOk())
                .andExpect(model().attribute("book", book))
                .andExpect(model().attribute("comments", comments))
                .andExpect(view().name("book/details"));
        verify(bookService, times(1)).getById("1");
        verify(commentService, times(1)).getCommentsByBookId("1");
    }

    @Test
    @DisplayName("отдавать страницу для редактирования книги")
    void shouldReturnToBookEditPage() throws Exception {
        val author = new AuthorDtoResponse("1", "some-name");
        val genre = new GenreDtoResponse("2", "some-genre");
        val book = new BookDtoResponse("2", "some-name", (short) 123, author, genre);
        val genres = IntStream.rangeClosed(1, 4)
                .mapToObj(n -> new GenreDtoResponse(String.valueOf(n), "g" + n))
                .toList();
        val authors = IntStream.rangeClosed(1, 5)
                .mapToObj(n -> new AuthorDtoResponse(String.valueOf(n), "a" + n))
                .toList();
        when(authorService.getAll()).thenReturn(authors);
        when(genreService.getAll()).thenReturn(genres);
        when(bookService.getById(book.id())).thenReturn(book);
        mvc.perform(get("/book/edit").param("id", book.id()))
                .andExpect(status().isOk())
                .andExpect(model().attribute("book", book))
                .andExpect(model().attribute("genres", genres))
                .andExpect(model().attribute("authors", authors))
                .andExpect(view().name("book/edit"));
        verify(bookService, times(1)).getById(book.id());
        verify(authorService, times(1)).getAll();
        verify(genreService, times(1)).getAll();
    }

}
