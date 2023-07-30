package ru.otus.homeworks.hw10.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homeworks.hw10.dto.CommentDtoRequest;
import ru.otus.homeworks.hw10.dto.CommentDtoResponse;
import ru.otus.homeworks.hw10.entity.Comment;
import ru.otus.homeworks.hw10.exception.EntityNotFoundException;
import ru.otus.homeworks.hw10.repository.BookRepository;
import ru.otus.homeworks.hw10.repository.CommentRepository;
import ru.otus.homeworks.hw10.service.CommentService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final BookRepository bookRepository;

    @Override
    @Transactional(readOnly = true)
    public List<CommentDtoResponse> getCommentsByBookId(String id) {
        return commentRepository.findAllByBookId(id).stream()
                .map(c -> new CommentDtoResponse(c.getId(), c.getMessage(), c.getUpdateOn()))
                .toList();
    }

    @Override
    @Transactional
    public void deleteById(String id) throws EntityNotFoundException {
        commentRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void add(CommentDtoRequest commentDto) throws EntityNotFoundException {
        val book = bookRepository.findById(commentDto.getBookId())
                .orElseThrow(() -> new EntityNotFoundException("Книга не найдена"));
        val comment = new Comment(commentDto.getMessage(), book);
        commentRepository.save(comment);
    }
}
