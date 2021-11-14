package com.app;

import com.data.shifts.decoratorpattern.*;

public class WorkScheduleDecPattern {
    public static void main(String[] args) {

        // using decorator pattern
        Shift morningShift = new Shift(BaseShift.MORNING);
        System.out.println(morningShift);
        morningShift = new Extend(morningShift, 30);
        System.out.println(morningShift);
        morningShift = new Extend(morningShift,-60);
        System.out.println(morningShift);
        morningShift = new StartOffset(morningShift, 60);
        System.out.println(morningShift);
    }

}
