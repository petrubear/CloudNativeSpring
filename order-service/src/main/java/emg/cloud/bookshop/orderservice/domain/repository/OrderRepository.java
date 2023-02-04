package emg.cloud.bookshop.orderservice.domain.repository;

import emg.cloud.bookshop.orderservice.domain.Order;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface OrderRepository extends ReactiveCrudRepository<Order, Long> {
}
