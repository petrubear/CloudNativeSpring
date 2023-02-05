package emg.cloud.bookshop.orderservice.domain.dto;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.test.StepVerifier;

import java.io.IOException;


@TestMethodOrder(MethodOrderer.Random.class)
class BookClientTest {
    private MockWebServer mockWebServer;
    private BookClient bookClient;

    @BeforeEach
    void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
        var webClient = WebClient.builder()
            .baseUrl(mockWebServer.url("/").toString())
            .build();

        bookClient = new BookClient(webClient);
    }

    @AfterEach
    void cleanup() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    void whenBookExistsThenReturnBook() {
        var bookIsbn = "1234567890";
        var mockResponse = new MockResponse()
            .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .setBody("""
                {
                    "isbn": "%s",
                    "title": "Test Book",
                    "author": "Test Author",
                    "price": 10.0,
                    "publisher": "Test Publisher"
                }
                """.formatted(bookIsbn));
        mockWebServer.enqueue(mockResponse);

        var book = bookClient.getBookByIsbn(bookIsbn);
        StepVerifier.create(book)
            .expectNextMatches(b -> b.isbn().equals(bookIsbn))
            .verifyComplete();
    }
}
