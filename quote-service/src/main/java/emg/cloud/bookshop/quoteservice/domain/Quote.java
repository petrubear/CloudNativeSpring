package emg.cloud.bookshop.quoteservice.domain;

public record Quote(
    String content,
    String author,
    Genre genre
) {
}
