package dev.lydtech.tracking.service;

import dev.lydtech.dispatch.message.OrderDispatched;
import dev.lydtech.tracking.message.OrderTracking;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * @author rahul
 */
@Service
@RequiredArgsConstructor
public class TrackingService {

    private static final String TRACKING_STATUS_TOPIC = "tracking.status";

    private final KafkaTemplate<String, Object> kafkaProducer;

    public void process(OrderDispatched orderDispatched) throws Exception {
        OrderTracking orderTracking = OrderTracking.builder()
                .orderId(orderDispatched.getOrderId())
                .status("tracking")
                .build();
        kafkaProducer.send(TRACKING_STATUS_TOPIC, orderTracking)
                .get();
    }
}
