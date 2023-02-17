package emg.cloud.bookshop.orderservice.domain.repository;

import emg.cloud.bookshop.orderservice.domain.Order;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface OrderRepository extends ReactiveCrudRepository<Order, Long> {
    Flux<Order> findAllByCreatedBy(String createdBy);
}
