package dev.lydtech.dispatch.service;

import dev.lydtech.dispatch.message.OrderCreated;
import org.springframework.stereotype.Service;

/**
 * @author rahul
 */
@Service
public class DispatchService {

    public void process(OrderCreated payload) {
        // no-op
    }
}
