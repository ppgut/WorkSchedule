package com.data.shifts;

import java.time.Duration;
import java.time.LocalTime;

public class Shift {
    BaseShift baseShift;
    String description;
    LocalTime startTime;
    Duration duration;
    Duration startOffset = Duration.ofMinutes(0);
    Duration shiftExtension = Duration.ofMinutes(0);

    public Shift(){}

    public Shift(BaseShift baseShift) {
        this.baseShift = baseShift;
        this.description = baseShift.description;
        this.startTime = baseShift.startTime;
        this.duration = baseShift.duration;
    }

    public BaseShift getBaseShift() {
        return baseShift;
    }
    public void setBaseShift(BaseShift baseShift) {
        this.baseShift = baseShift;
    }

    public String getDescription() {
        return description;
    }
    public LocalTime getStartTime() {
        return startTime.plus(startOffset);
    }
    public Duration getDuration() {
        return duration.plus(shiftExtension);
    }
    public LocalTime getEndTime() {
        return getStartTime().plus(getDuration());
    }

    public Duration getStartOffset() {
        return startOffset;
    }
    public void setStartOffset(int minutes) {
        startOffset = Duration.ofMinutes(minutes);
    }
    public void startLater(int minutes) {
        startOffset = startOffset.plus(Duration.ofMinutes(minutes));
    }
    public Duration getShiftExtension() {
        return shiftExtension;
    }
    public void setShiftExtension(int minutes) {
        shiftExtension = Duration.ofMinutes(minutes);
    }
    public void extendShift(int minutes) {
        shiftExtension = shiftExtension.plus(Duration.ofMinutes(minutes));
    }

    @Override
    public String toString() {
        return
                "Shift{" + getDescription() + ": " +
                        "start=" + getStartTime().toString() +
                        ", end=" + getEndTime().toString() +
                        ", duration=" + getDuration().toHoursPart() + "h" + getDuration().toMinutesPart() + "min}";
    }
}
