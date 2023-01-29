package emg.cloud.bookshop.catalogservice.domain;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.time.Instant;

public record Book(
    @Id
    Long id,
    @NotBlank(message = "ISBN is mandatory")
    @Pattern(regexp = "^([0-9]{10}|[0-9]{13})",
        message = "ISBN format must be valid")
    String isbn,
    @NotBlank(message = "Title is mandatory")
    String title,
    @NotBlank(message = "Author is mandatory")
    String author,
//    @NotBlank(message = "Price is mandatory")
    @Positive(message = "Price must be greater than 0")
    Double price,
    String publisher,
    @CreatedDate
    Instant createdDate,
    @LastModifiedDate
    Instant lastModifiedDate,
    @Version
    int version
) {
    public static Book of(
        String isbn,
        String title,
        String author,
        Double price,
        String publisher
    ) {
        return new Book(null, isbn, title, author, price, publisher, null, null, 0);
    }
}
