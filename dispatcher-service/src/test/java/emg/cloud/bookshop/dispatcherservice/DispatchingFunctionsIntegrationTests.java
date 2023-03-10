package emg.cloud.bookshop.dispatcherservice;

import emg.cloud.bookshop.dispatcherservice.model.OrderAcceptedMessage;
import emg.cloud.bookshop.dispatcherservice.model.OrderDispatchedMessage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.function.context.FunctionCatalog;
import org.springframework.cloud.function.context.test.FunctionalSpringBootTest;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.function.Function;

@FunctionalSpringBootTest
public class DispatchingFunctionsIntegrationTests {

    @Autowired
    private FunctionCatalog functionCatalog;

    @Test
    void packAndLabelOrder() {
        Function<OrderAcceptedMessage, Flux<OrderDispatchedMessage>> packAndLabel = functionCatalog.lookup(Function.class,
            "pack|label");
        var orderId = 121L;
        StepVerifier.create(packAndLabel.apply(
            new OrderAcceptedMessage(orderId)
        )).expectNextMatches(dispatchedOrder ->
            dispatchedOrder.equals(new OrderDispatchedMessage(orderId))
        ).verifyComplete();
    }
}
