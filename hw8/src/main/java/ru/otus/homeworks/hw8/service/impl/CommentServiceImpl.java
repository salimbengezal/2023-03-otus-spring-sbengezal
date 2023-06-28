package ru.otus.homeworks.hw8.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homeworks.hw8.entity.Comment;
import ru.otus.homeworks.hw8.exceptions.EntityNotFoundException;
import ru.otus.homeworks.hw8.repositories.CommentRepository;
import ru.otus.homeworks.hw8.service.BookService;
import ru.otus.homeworks.hw8.service.CommentService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final BookService bookService;

    @Override
    @Transactional(readOnly = true)
    public List<Comment> getCommentsByBookId(String id) {
        return commentRepository.findAllByBookId(id);
    }

    @Override
    @Transactional
    public void deleteById(String id) throws EntityNotFoundException {
        val comment = getById(id);
        commentRepository.delete(comment);
    }

    @Override
    public Comment getById(String id) throws EntityNotFoundException {
        return commentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Комментарий не найден"));
    }

    @Override
    @Transactional
    public Comment update(String id, String newMessage) throws EntityNotFoundException {
        val comment = getById(id);
        comment.setMessage(newMessage);
        comment.setUpdateOn(LocalDateTime.now());
        return commentRepository.save(comment);
    }

    @Override
    @Transactional
    public Comment add(String id, String message) throws EntityNotFoundException {
        val book = bookService.getById(id);
        val comment = Comment.builder()
                .book(book)
                .message(message)
                .updateOn(LocalDateTime.now())
                .build();
        return commentRepository.save(comment);
    }
}
