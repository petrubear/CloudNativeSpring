package emg.cloud.bookshop.catalogservice.demo;

import emg.cloud.bookshop.catalogservice.domain.Book;
import emg.cloud.bookshop.catalogservice.domain.repository.BookRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Profile("testData")
@Component
public class BookDataLoader {
    private final BookRepository bookRepository;

    public BookDataLoader(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void loadBookTestData() {
        bookRepository.deleteAll();
        var books = List.of(Book.of("978-1-78528-415-1", "The Hitchhiker's Guide to the Galaxy", "Douglas Adams", 9.90, null),
            Book.of("978-1-4028-9462-6", "Dune", "Frank Herbert", 12.78, null),
            Book.of("978-0-553-57340-3", "Foundation", "Isaac Asimov", 23.23, null),
            Book.of("978-0-553-21311-0", "Fahrenheit 451", "Ray Bradbury", 13.40, null));
        bookRepository.saveAll(books);
    }
}
