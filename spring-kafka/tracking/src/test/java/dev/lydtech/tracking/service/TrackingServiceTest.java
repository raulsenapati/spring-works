package dev.lydtech.tracking.service;

import dev.lydtech.dispatch.message.OrderDispatched;
import dev.lydtech.dispatch.message.OrderTracking;
import dev.lydtech.tracking.util.TestEventData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.concurrent.CompletableFuture;

import static java.util.UUID.randomUUID;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

/**
 * @author rahul
 */
public class TrackingServiceTest {

    private static final String TRACKING_STATUS_TOPIC = "tracking.status";

    private TrackingService service;

    private KafkaTemplate kafkaProducerMock;

    @BeforeEach
    void setup() {
        kafkaProducerMock = mock(KafkaTemplate.class);
        service = new TrackingService(kafkaProducerMock);
    }

    @Test
    void process_Success() throws Exception {
        when(kafkaProducerMock.send(anyString(), any(OrderTracking.class)))
                .thenReturn(mock(CompletableFuture.class));
        OrderDispatched testEvent = TestEventData.buildOrderDispatchedEvent(randomUUID());
        service.process(testEvent);
        verify(kafkaProducerMock, times(1))
                .send(eq(TRACKING_STATUS_TOPIC), any(OrderTracking.class));
    }

    @Test
    void process_ProducerThrowsException() throws Exception {
        OrderDispatched testEvent = TestEventData.buildOrderDispatchedEvent(randomUUID());
        doThrow(new RuntimeException("Producer failure"))
                .when(kafkaProducerMock)
                .send(eq(TRACKING_STATUS_TOPIC), any(OrderTracking.class));
        Exception exception = assertThrows(RuntimeException.class, () -> service.process(testEvent));

        verify(kafkaProducerMock, times(1))
                .send(eq(TRACKING_STATUS_TOPIC), any(OrderTracking.class));
        assertThat(exception.getMessage(), equalTo("Producer failure"));
    }
}
