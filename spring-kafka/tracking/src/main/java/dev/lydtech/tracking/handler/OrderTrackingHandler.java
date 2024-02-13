package dev.lydtech.tracking.handler;

import dev.lydtech.dispatch.message.OrderDispatched;
import dev.lydtech.tracking.service.TrackingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author rahul
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class OrderTrackingHandler {

    private final TrackingService trackingService;

    @KafkaListener(
            id = "dispatchConsumerClient",
            topics = "dispatch.tracking",
            groupId = "dispatch.order.tracking.consumer",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void listen(OrderDispatched payload) {
        log.info("Received message: payload" + payload);
        try {
            trackingService.process(payload);
        } catch (Exception e) {
            log.error("Processing failure", e);
        }
    }
}
