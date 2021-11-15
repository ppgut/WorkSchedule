package com.data.schedule;

import com.data.date.*;
import com.data.employees.*;
import com.data.shifts.*;

import javax.persistence.*;
import java.util.Objects;

import static com.app.WorkSchedule.scheduleLineDAO;

@Entity
@Table(name = "schedule")
public class ScheduleLine extends com.data.Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "day_id")
    private Day day;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Column(name = "base_shift")
    private String baseShift;

    @Column(name = "shift_start_offset")
    private int startOffset; // minutes

    @Column(name = "shift_extension")
    private int shiftExtension; // minutes

    public ScheduleLine(){}

    public ScheduleLine(Day day, Employee employee, Shift shift) {
        setDay(day);
        setEmployee(employee);
        this.baseShift = shift.getBaseShift().toString();
        this.startOffset = (int) shift.getStartOffset().toMinutes();
        this.shiftExtension = (int) shift.getShiftExtension().toMinutes();
        this.id = scheduleLineDAO.findId(this);
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Day getDay() {
        return this.day;
    }

    public void setDay(Day day) {
        if (this.day != null)
            this.day.removeScheduleLine(this);
        this.day = day;
        this.day.addScheduleLine(this);
    }

    public Employee getEmployee() {
        return this.employee;
    }

    public void setEmployee(Employee employee) {
        if (this.employee != null)
            this.employee.removeScheduleLine(this);
        this.employee = employee;
        this.employee.addScheduleLine(this);
    }

    public String getBaseShift() {
        return this.baseShift;
    }

    public void setBaseShift(String baseShift) {
        this.baseShift = baseShift;
    }

    public int getStartOffset() {
        return startOffset;
    }

    public void setStartOffset(int startOffset) {
        this.startOffset = startOffset;
    }

    public int getShiftExtension() {
        return shiftExtension;
    }

    public void setShiftExtension(int shiftExtension) {
        this.shiftExtension = shiftExtension;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScheduleLine that = (ScheduleLine) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
