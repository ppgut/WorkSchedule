package com.data.shifts.decoratorpattern;

import java.time.Duration;
import java.time.LocalTime;

public class Extend extends ShiftModifier {

    public Extend(Shift shift, int minutes) {
        super(shift, minutes);
    }

    @Override
    public LocalTime getStartTime() {
        return shift.getStartTime();
    }

    @Override
    public Duration getDuration() {
        return shift.getDuration().plus(Duration.ofMinutes(minutes));
    }
}
