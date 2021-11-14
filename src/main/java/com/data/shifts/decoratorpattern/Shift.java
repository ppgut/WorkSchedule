package com.data.shifts.decoratorpattern;

import java.time.Duration;
import java.time.LocalTime;

public class Shift {
    BaseShift baseShift;
    String description;
    LocalTime startTime;
    Duration duration;

    public Shift(){}

    public Shift(BaseShift baseShift) {
        this.baseShift = baseShift;
        this.description = baseShift.description;
        this.startTime = baseShift.startTime;
        this.duration = baseShift.duration;
    }

    public String getDescription() {
        return this.description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public LocalTime getStartTime() {
        return this.startTime;
    }
    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }
    public Duration getDuration() {
        return this.duration;
    }
    public void setDuration(Duration duration) {
        this.duration = duration;
    }
    public LocalTime getEndTime() {
        return this.getStartTime().plus(this.getDuration());
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
