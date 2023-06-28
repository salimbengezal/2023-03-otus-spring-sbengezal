package ru.otus.homeworks.hw8.events;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterDeleteEvent;
import org.springframework.stereotype.Component;
import ru.otus.homeworks.hw8.entity.Book;
import ru.otus.homeworks.hw8.entity.Comment;
import ru.otus.homeworks.hw8.repositories.CommentRepository;

@Component
@RequiredArgsConstructor
public class CommentCascadeDelete extends AbstractMongoEventListener<Book> {

    private final CommentRepository commentRepository;

    @Override
    public void onAfterDelete(AfterDeleteEvent<Book> event) {
        val bookId = event.getSource().get("_id").toString();
        val commentsIds = commentRepository.findAllByBookId(bookId).stream().map(Comment::getId).toList();
        super.onAfterDelete(event);
        commentRepository.deleteAllById(commentsIds);
    }
}
