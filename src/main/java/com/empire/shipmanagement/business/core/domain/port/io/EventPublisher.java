package com.empire.shipmanagement.business.core.domain.port.io;

import com.empire.shipmanagement.business.core.domain.model.EventType;

public interface EventPublisher {
    <T> void publish(String id, EventType type, T data);
}
