package emg.cloud.bookshop.catalogservice;

import emg.cloud.bookshop.catalogservice.configuration.SecurityConfig;
import emg.cloud.bookshop.catalogservice.domain.exceptions.BookNotFoundException;
import emg.cloud.bookshop.catalogservice.domain.service.BookService;
import emg.cloud.bookshop.catalogservice.web.BookController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(BookController.class)
@Import(SecurityConfig.class)
class BookControllerMvcTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @MockBean
    JwtDecoder jwtDecoder;

    @Test
    void whenGetBookNotExistingThenShouldReturn404() throws Exception {
        var isbn = "0000000000";
        given(bookService.viewBookDetails(isbn))
            .willThrow(BookNotFoundException.class);
        mockMvc.perform(get("/books/{isbn}", isbn))
            .andExpect(status().isNotFound());
    }

    @Test
    void whenDeleteBookWithEmployeeRoleThenShouldReturn204()
        throws Exception {
        var isbn = "7373731394";
        mockMvc
            .perform(MockMvcRequestBuilders.delete("/books/" + isbn)
                .with(SecurityMockMvcRequestPostProcessors.jwt()
                    .authorities(new SimpleGrantedAuthority("ROLE_employee"))))
            .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    void whenDeleteBookWithCustomerRoleThenShouldReturn403()
        throws Exception {
        var isbn = "7373731394";
        mockMvc
            .perform(MockMvcRequestBuilders.delete("/books/" + isbn)
                .with(SecurityMockMvcRequestPostProcessors.jwt()
                    .authorities(new SimpleGrantedAuthority("ROLE_customer"))))
            .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    void whenDeleteBookNotAuthenticatedThenShouldReturn401()
        throws Exception {
        var isbn = "7373731394";
        mockMvc
            .perform(MockMvcRequestBuilders.delete("/books/" + isbn))
            .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }
}
