package dev.lydtech.tracking.integration;

import dev.lydtech.dispatch.message.OrderTracking;
import dev.lydtech.tracking.TrackingConfiguration;
import dev.lydtech.tracking.util.TestEventData;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.ContainerTestUtils;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.equalTo;

/**
 * @author rahul
 */
@Slf4j
@SpringBootTest(classes = {TrackingConfiguration.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@ActiveProfiles("test")
@EmbeddedKafka(controlledShutdown = true, partitions = 1)
public class OrderTrackingIntegrationTest {
    private static final String DISPATCH_TRACKING_TOPIC = "dispatch.tracking";

    private static final String TRACKING_STATUS_TOPIC = "tracking.status";

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Autowired
    private KafkaTestListener testListener;

    @Autowired
    private EmbeddedKafkaBroker embeddedKafkaBroker;

    @Autowired
    private KafkaListenerEndpointRegistry registry;

    @BeforeEach
    public void setUp() {
        testListener.orderTrackingCounter.set(0);

        registry.getListenerContainers()
                .stream()
                .forEach(container ->
                        ContainerTestUtils.waitForAssignment(container, embeddedKafkaBroker.getPartitionsPerTopic()));
    }

    @Test
    public void testOrderTrackingFlow() throws Exception {
        var orderDispatched = TestEventData.buildOrderDispatchedEvent(UUID.randomUUID());
        sendMessage(DISPATCH_TRACKING_TOPIC, orderDispatched);

        await().atMost(13, TimeUnit.SECONDS).pollDelay(100, TimeUnit.MILLISECONDS)
                .until(testListener.orderTrackingCounter::get, equalTo(1));
    }

    private void sendMessage(String topic, Object data) throws Exception {
        kafkaTemplate.send(MessageBuilder
                        .withPayload(data)
                        .setHeader(KafkaHeaders.TOPIC, topic)
                        .build())
                .get();
    }

    @Configuration
    static class TestConfig {

        @Bean
        public KafkaTestListener testListener() {
            return new KafkaTestListener();
        }
    }

    public static class KafkaTestListener {

        AtomicInteger orderTrackingCounter = new AtomicInteger(0);

        @KafkaListener(groupId = "KafkaMessageListenerContainer", topics = TRACKING_STATUS_TOPIC)
        void receiveOrderTracking(@Payload OrderTracking payload) {
            log.debug("Received OrderTracking: {}", payload);
            orderTrackingCounter.incrementAndGet();
        }
    }
}
