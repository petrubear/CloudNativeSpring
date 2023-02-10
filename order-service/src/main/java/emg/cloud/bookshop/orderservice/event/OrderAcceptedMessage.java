package emg.cloud.bookshop.orderservice.event;

public record OrderAcceptedMessage(
    Long orderId
) {
}
