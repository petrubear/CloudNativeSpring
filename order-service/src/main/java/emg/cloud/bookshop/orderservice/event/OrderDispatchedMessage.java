package emg.cloud.bookshop.orderservice.event;

public record OrderDispatchedMessage(
    Long orderId
) {
}
