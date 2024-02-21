package dev.lydtech.dispatch.service;

import dev.lydtech.dispatch.message.OrderCreated;
import dev.lydtech.dispatch.message.OrderDispatched;
import dev.lydtech.dispatch.util.TestEventData;
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
class DispatchServiceTest {

    private static final String ORDER_DISPATCHED_TOPIC = "order.dispatched";

    private static final String DISPATCH_TRACKING_TOPIC = "dispatch.tracking";
    private DispatchService service;

    private KafkaTemplate kafkaProducerMock;

    @BeforeEach
    void setUp() {
        kafkaProducerMock = mock(KafkaTemplate.class);
        service = new DispatchService(kafkaProducerMock);
    }

    @Test
    void process_Success() throws Exception {
        when(kafkaProducerMock.send(anyString(), any(OrderDispatched.class)))
                .thenReturn(mock(CompletableFuture.class));
        OrderCreated testEvent = TestEventData.buildOrderCreatedEven(randomUUID(), randomUUID().toString());
        service.process(testEvent);
        verify(kafkaProducerMock, times(1))
                .send(eq(ORDER_DISPATCHED_TOPIC), any(OrderDispatched.class));
        verify(kafkaProducerMock, times(1))
                .send(eq(DISPATCH_TRACKING_TOPIC), any(OrderDispatched.class));
    }

    @Test
    void process_ProducerThrowsException() throws Exception {
        OrderCreated testEvent = TestEventData.buildOrderCreatedEven(randomUUID(), randomUUID().toString());
        doThrow(new RuntimeException("Producer failure"))
                .when(kafkaProducerMock)
                .send(eq(ORDER_DISPATCHED_TOPIC), any(OrderDispatched.class));
        Exception exception = assertThrows(RuntimeException.class, () -> service.process(testEvent));

        verify(kafkaProducerMock, times(1))
                .send(eq(ORDER_DISPATCHED_TOPIC), any(OrderDispatched.class));
        assertThat(exception.getMessage(), equalTo("Producer failure"));
    }

    @Test
    void process_ProducerDispatchTrackingThrowsException() throws Exception {
        OrderCreated testEvent = TestEventData.buildOrderCreatedEven(randomUUID(), randomUUID().toString());
        doThrow(new RuntimeException("Producer failure"))
                .when(kafkaProducerMock)
                .send(eq(ORDER_DISPATCHED_TOPIC), any(OrderDispatched.class));
        Exception exception = assertThrows(RuntimeException.class, () -> service.process(testEvent));

        verify(kafkaProducerMock, times(1))
                .send(eq(ORDER_DISPATCHED_TOPIC), any(OrderDispatched.class));
//        verify(kafkaProducerMock, times(1))
//                .send(eq(DISPATCH_TRACKING_TOPIC), any(OrderDispatched.class));
        assertThat(exception.getMessage(), equalTo("Producer failure"));
    }
}