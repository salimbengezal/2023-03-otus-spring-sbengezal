package ru.otus.homeworks.hw10.events;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterDeleteEvent;
import org.springframework.stereotype.Component;
import ru.otus.homeworks.hw10.entity.Book;
import ru.otus.homeworks.hw10.repository.CommentRepository;

@Component
@RequiredArgsConstructor
public class BookDeleteListener extends AbstractMongoEventListener<Book> {

    private final CommentRepository commentRepository;

    @Override
    public void onAfterDelete(AfterDeleteEvent<Book> event) {
        val bookId = event.getSource().get("_id").toString();
        commentRepository.deleteAllByBookId(bookId);
        super.onAfterDelete(event);
    }
}
