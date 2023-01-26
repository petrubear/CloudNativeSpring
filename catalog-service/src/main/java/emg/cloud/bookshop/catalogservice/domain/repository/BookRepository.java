package emg.cloud.bookshop.catalogservice.domain.repository;

import emg.cloud.bookshop.catalogservice.domain.Book;

import java.util.Optional;

public interface BookRepository {
    Iterable<Book> findAll();

    Optional<Book> findByIsbn(String isbn);

    Book save(Book book);

    void deleteByIsbn(String isbn);

    boolean existsByIsbn(String isbn);
}
