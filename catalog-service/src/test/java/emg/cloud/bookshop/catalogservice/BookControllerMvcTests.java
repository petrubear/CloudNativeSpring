package emg.cloud.bookshop.catalogservice;

import emg.cloud.bookshop.catalogservice.domain.exceptions.BookNotFoundException;
import emg.cloud.bookshop.catalogservice.domain.service.BookService;
import emg.cloud.bookshop.catalogservice.web.BookController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(BookController.class)
class BookControllerMvcTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Test
    void whenGetBookNotExistingThenShouldReturn404() throws Exception {
        var isbn = "0000000000";
        given(bookService.viewBookDetails(isbn))
            .willThrow(BookNotFoundException.class);
        mockMvc.perform(get("/books/{isbn}", isbn))
            .andExpect(status().isNotFound());
    }
}
