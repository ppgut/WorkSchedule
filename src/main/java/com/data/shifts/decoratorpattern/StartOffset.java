package com.data.shifts.decoratorpattern;

import java.time.Duration;
import java.time.LocalTime;

public class StartOffset extends ShiftModifier {

    public StartOffset(Shift shift, int minutes) {
        super(shift,minutes);
    }

    @Override
    public LocalTime getStartTime() {
        return shift.getStartTime().plus(Duration.ofMinutes(minutes));
    }

    @Override
    public Duration getDuration() {
        return shift.getDuration();
    }
}
