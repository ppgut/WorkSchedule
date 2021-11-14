package com.data.shifts.decoratorpattern;

import java.time.Duration;
import java.time.LocalTime;

public class ShiftInterpreter {

    private static int[] getOffsets(Shift shift, String shiftDesignation) {
        int startTimeOffset = 0;
        int endTimeOffset = 0;
        int shiftDescriptionIndex = shiftDesignation.indexOf(shift.description);
        for (int i = 0; i < shiftDesignation.length(); i++) {
            if (i < shiftDescriptionIndex) {
                switch (shiftDesignation.charAt(i)) {
                    case '+' -> startTimeOffset++;
                    case '-' -> startTimeOffset--;
                    case '.' -> {startTimeOffset--; endTimeOffset--;}
                }
            } else if (i > shiftDescriptionIndex) {
                switch (shiftDesignation.charAt(i)) {
                    case '+' -> endTimeOffset++;
                    case '-' -> endTimeOffset--;
                    case '.' -> {startTimeOffset++; endTimeOffset++;}
                }
            }
        }
        return new int[]{startTimeOffset, endTimeOffset};
    }

    public static LocalTime getStartTime(Shift shift, String shiftDesignation) {
        int[] offsets = getOffsets(shift, shiftDesignation);
        int startTimeOffset = offsets[0];
        return shift.baseShift.startTime.plus(Duration.ofMinutes(startTimeOffset * 30));
    }

    public static LocalTime getEndTime(Shift shift, String shiftDesignation) {
        int[] offsets = getOffsets(shift, shiftDesignation);
        int endTimeOffset = offsets[0];
        return shift.baseShift.startTime.plus(shift.baseShift.duration).plus(Duration.ofMinutes(endTimeOffset * 30));
    }

    public static Duration getDuration(Shift shift, String shiftDesignation) {
        int[] offsets = getOffsets(shift, shiftDesignation);
        int startTimeOffset = offsets[0];
        int endTimeOffset = offsets[1];
        return shift.getDuration().plus(Duration.ofMinutes((- startTimeOffset + endTimeOffset) * 30));
    }

    public static String getDurationString(Shift shift, String shiftDesignation) {
        Duration duration = getDuration(shift, shiftDesignation);
        return duration.toHoursPart() + "h " + duration.toMinutesPart() + "min";
    }

}
