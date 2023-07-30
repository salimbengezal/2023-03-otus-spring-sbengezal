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
import ru.otus.homeworks.hw10.service.BookDetailsService;

import java.time.LocalDateTime;
import java.util.stream.IntStream;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookDetailsController.class)
@DisplayName("Контроллер с деталями книги должен ")
public class BookDetailsControllerTest {

    @MockBean
    private BookDetailsService bookDetailsService;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;


    @Test
    @DisplayName("возвращать книгу с комментариями")
    void shouldGetBookWithComments() throws Exception {
        val author = new AuthorDtoResponse("a_id", "a_name");
        val genre = new GenreDtoResponse("a_id", "a_name");
        val book = new BookDtoResponse("id", "name", (short) 123, author, genre);
        val comments = IntStream.rangeClosed(1, 4)
                .mapToObj(number -> new CommentDtoResponse("id" + number, "message" + number,
                        LocalDateTime.now()))
                .toList();
        val bookDto = new BookDetailsDtoResponse(book, comments);
        when(bookDetailsService.getById(book.id())).thenReturn(bookDto);
        val bookJson = mapper.writeValueAsString(bookDto);
        mvc.perform(get("/api/book/%s".formatted(book.id()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(bookJson));
        verify(bookDetailsService, times(1)).getById(book.id());
    }

}
