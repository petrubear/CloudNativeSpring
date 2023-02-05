package emg.cloud.bookshop.orderservice.domain.dto;

public record Book(
    String isbn,
    String title,
    String author,
    Double price
) {
}
