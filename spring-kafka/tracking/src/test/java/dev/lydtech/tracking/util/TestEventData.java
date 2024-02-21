package dev.lydtech.tracking.util;

import dev.lydtech.dispatch.message.OrderDispatched;

import java.util.UUID;

/**
 * @author rahul
 */
public class TestEventData {

    public static OrderDispatched buildOrderDispatchedEvent(UUID orderId) {
        return OrderDispatched.builder()
                .orderId(orderId)
                .build();
    }
}
