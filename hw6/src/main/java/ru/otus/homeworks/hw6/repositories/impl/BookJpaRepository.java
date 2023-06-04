package ru.otus.homeworks.hw6.repositories.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homeworks.hw6.entity.Book;
import ru.otus.homeworks.hw6.repositories.BookRepository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BookJpaRepository implements BookRepository {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public List<Book> getAll() {
        val entityGraph = em.getEntityGraph("book-author-genre-entity-graph");
        val query = em.createQuery("select b from book b", Book.class);
        query.setHint("javax.persistence.fetchgraph", entityGraph);
        return query.getResultList();
    }

    @Override
    public Optional<Book> getById(long id) {
        return Optional.ofNullable(em.find(Book.class, id));
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        val query = em.createQuery("delete from book b where b.id=:id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    @Transactional
    public Book save(Book book) {
        if (book.getId() == 0) {
            em.persist(book);
        } else {
            em.merge(book);
        }
        return book;
    }

}
