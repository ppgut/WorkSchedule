package com.data.shifts.decoratorpattern;

import java.time.Duration;
import java.time.LocalTime;

public abstract class ShiftModifier extends Shift {

    Shift shift;
    int minutes;

    public ShiftModifier(Shift shift, int minutes) {
        this.shift = shift;
        this.minutes = minutes;
    }

    @Override
    public String getDescription() {
        return shift.getDescription();
    }

    public abstract LocalTime getStartTime();
    public abstract Duration getDuration();
}
