package ru.otus.homeworks.hw7.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homeworks.hw7.entity.Comment;
import ru.otus.homeworks.hw7.exceptions.EntityNotFoundException;
import ru.otus.homeworks.hw7.repositories.BookRepository;
import ru.otus.homeworks.hw7.repositories.CommentRepository;
import ru.otus.homeworks.hw7.service.CommentService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final BookRepository bookRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Comment> getAllByBookId(long id) throws EntityNotFoundException {
        val book = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Комментарий [id=%s] не найден".formatted(id)));
        return new ArrayList<>(book.getComments());
    }

    @Override
    @Transactional
    public void deleteById(long id) throws EntityNotFoundException {
        val comment = commentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Комментарий [id=%s] не найден".formatted(id)));
        commentRepository.delete(comment);
    }

    @Override
    public Comment getById(long id) throws EntityNotFoundException {
        return commentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Комментарий [id=%s] не найден".formatted(id)));
    }

    @Override
    @Transactional
    public Comment update(long id, String newMessage) throws EntityNotFoundException {
        val comment = commentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Комментарий [id=%s] не найден".formatted(id)));
        comment.setMessage(newMessage);
        return commentRepository.save(comment);
    }

    @Override
    @Transactional
    public Comment add(long id, String message) throws EntityNotFoundException {
        val book = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Книга [id=%s] не найдена".formatted(id)));
        val comment = Comment.builder().book(book).message(message).build();
        return commentRepository.save(comment);
    }
}
