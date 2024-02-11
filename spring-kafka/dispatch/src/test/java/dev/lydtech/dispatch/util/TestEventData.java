package dev.lydtech.dispatch.util;

import dev.lydtech.dispatch.message.OrderCreated;

import java.util.UUID;

/**
 * @author rahul
 */
public class TestEventData {

    public static OrderCreated buildOrderCreatedEven(UUID orderId, String item) {
        return OrderCreated.builder()
                .orderId(orderId)
                .item(item)
                .build();
    }
}
