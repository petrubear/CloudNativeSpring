package emg.cloud.bookshop.quotefunction.domain;

public record Quote(
    String content,
    String author,
    Genre genre
) {
}
