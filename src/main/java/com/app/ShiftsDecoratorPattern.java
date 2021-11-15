package com.app;

import com.data.shifts.decoratorpattern.*;

public class ShiftsDecoratorPattern {
    public static void main(String[] args) {

        // modify shifts start_time and shift_extension using decorator pattern
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
