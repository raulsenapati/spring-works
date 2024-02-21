package dev.lydtech.dispatch.service;

import dev.lydtech.dispatch.message.OrderCreated;
import dev.lydtech.dispatch.message.OrderDispatched;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * @author rahul
 */
@Service
@RequiredArgsConstructor
public class DispatchService {

    private static final String ORDER_DISPATCHED_TOPIC = "order.dispatched";

    private static final String DISPATCH_TRACKING_TOPIC = "dispatch.tracking";

    private final KafkaTemplate<String, Object> kafkaProducer;

    public void process(OrderCreated orderCreated) throws Exception {
        OrderDispatched orderDispatched = OrderDispatched.builder()
                .orderId(orderCreated.getOrderId())
                .build();
        kafkaProducer.send(ORDER_DISPATCHED_TOPIC, orderDispatched)
                .get();
        track(orderDispatched);
    }

    public void track(OrderDispatched orderDispatched) throws Exception {
        kafkaProducer.send(DISPATCH_TRACKING_TOPIC, orderDispatched)
                .get();
    }
}
