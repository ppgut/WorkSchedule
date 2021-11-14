package com.data.shifts.decoratorpattern;

import java.time.Duration;
import java.time.LocalTime;

public enum BaseShift {
    MORNING("R", LocalTime.parse("08:00"), Duration.ofHours(8)),
    AFTERNOON("P", LocalTime.parse("13:00"), Duration.ofHours(8)),
    DAY("D", LocalTime.parse("08:00"), Duration.ofHours(12)),
    NIGHT("N", LocalTime.parse("20:00"), Duration.ofHours(12)),
    DAY_OFF("W", LocalTime.parse("00:00"), Duration.ofHours(0)),
    HOLIDAY("U", LocalTime.parse("00:00"), Duration.ofHours(8));

    String description;
    LocalTime startTime;
    Duration duration;

    BaseShift(String description,
              LocalTime startTime,
              Duration duration) {
        this.description = description;
        this.startTime = startTime;
        this.duration = duration;
    }
}
