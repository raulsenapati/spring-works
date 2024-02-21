package dev.lydtech.dispatch.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * @author rahul
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderTracking {

    UUID orderId;

    String status;
}
