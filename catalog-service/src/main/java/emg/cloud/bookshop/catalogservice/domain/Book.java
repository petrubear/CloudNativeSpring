package emg.cloud.bookshop.catalogservice.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

public record Book(
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
    Double price
) {
}
