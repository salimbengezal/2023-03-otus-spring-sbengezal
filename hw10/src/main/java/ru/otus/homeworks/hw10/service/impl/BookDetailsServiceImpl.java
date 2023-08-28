package ru.otus.homeworks.hw10.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import ru.otus.homeworks.hw10.dto.BookDetailsDtoResponse;
import ru.otus.homeworks.hw10.exception.EntityNotFoundException;
import ru.otus.homeworks.hw10.mapper.BookMapper;
import ru.otus.homeworks.hw10.repository.BookRepository;
import ru.otus.homeworks.hw10.repository.CommentRepository;
import ru.otus.homeworks.hw10.service.BookDetailsService;

@Service
@RequiredArgsConstructor
public class BookDetailsServiceImpl implements BookDetailsService {

    private final BookRepository bookRepository;

    private final CommentRepository commentRepository;

    private final BookMapper mapper;

    @Override
    public BookDetailsDtoResponse getById(String id) throws EntityNotFoundException {
        val book = bookRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Книга не найдена"));
        val comments = commentRepository.findAllByBookId(book.getId());
        return mapper.toDto(book, comments);
    }

}
