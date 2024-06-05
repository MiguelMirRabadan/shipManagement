package com.empire.shipmanagement.business.core.application.event;

import com.empire.shipmanagement.business.core.domain.model.EventType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class Event<T> {
    private String id;
    private LocalDate date;
    private EventType type;
    private T data;

}
