package dev.lydtech.tracking.handler;

import dev.lydtech.dispatch.message.OrderDispatched;
import dev.lydtech.tracking.service.TrackingService;
import dev.lydtech.tracking.util.TestEventData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static java.util.UUID.randomUUID;
import static org.mockito.Mockito.*;

/**
 * @author rahul
 */
public class OrderTrackingHandlerTest {

    private OrderTrackingHandler handler;

    private TrackingService trackingServiceMock;

    @BeforeEach
    void setup() {
        trackingServiceMock = mock(TrackingService.class);
        handler = new OrderTrackingHandler(trackingServiceMock);
    }

    @Test
    void listen_Success() throws Exception {
        OrderDispatched testEvent = TestEventData.buildOrderDispatchedEvent(randomUUID());
        handler.listen(testEvent);
        verify(trackingServiceMock, times(1))
                .process(testEvent);
    }

    @Test
    void listen_ServiceThrowsException() throws Exception {
        OrderDispatched testEvent = TestEventData.buildOrderDispatchedEvent(randomUUID());
        doThrow(new RuntimeException("Service failure"))
                .when(trackingServiceMock)
                .process(testEvent);

        handler.listen(testEvent);
        verify(trackingServiceMock, times(1))
                .process(testEvent);
    }
}
