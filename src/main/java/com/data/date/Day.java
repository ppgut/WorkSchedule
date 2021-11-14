package com.data.date;

import com.data.schedule.*;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Table(name = "day")
@Entity
public class Day extends com.data.Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NaturalId
    @Column(name = "fulldate", nullable = false, unique = true,length = 50)
    private String fulldate;

    @Column(name = "year")
    private Integer year;

    @Column(name = "month")
    private Integer month;

    @Column(name = "day_of_month")
    private Integer dayOfMonth;

    @Column(name = "day_of_week")
    private Integer dayOfWeek;

    @Column(name = "is_weekend")
    private Boolean isWeekend;

    @Column(name = "is_holiday")
    private Boolean isHoliday;

    // @OneToMany(mappedBy = "day")
    // private Set<ScheduleLine> scheduleLines = new HashSet<>();

    public Day() {}

    public Day(LocalDate date) {
        this.fulldate = date.toString();
        this.year = date.getYear();
        this.month = date.getMonthValue();
        this.dayOfMonth = date.getDayOfMonth();
        this.dayOfWeek = date.getDayOfWeek().getValue();
        this.isWeekend = this.dayOfWeek > 5;

        // TODO prepare list of holidays in database and check fullDate vs this list
        this.isHoliday = false;
    }

    public Boolean getIsHoliday() {
        return isHoliday;
    }

    public void setIsHoliday(Boolean isHoliday) {
        this.isHoliday = isHoliday;
    }

    public Boolean getIsWeekend() {
        return isWeekend;
    }

    public void setIsWeekend(Boolean isWeekend) {
        this.isWeekend = isWeekend;
    }

    public Integer getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(Integer dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Integer getDayOfMonth() {
        return dayOfMonth;
    }

    public void setDayOfMonth(Integer dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getFulldate() {
        return fulldate;
    }

    public void setFulldate(LocalDate date) {
        this.fulldate = date.toString();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    // public Set<ScheduleLine> getScheduleLines() {
    //     return this.scheduleLines;
    // }
    //
    // public void setScheduleLines(Set<ScheduleLine> scheduleLines) {
    //     this.scheduleLines = scheduleLines;
    // }
    //
    // public void addScheduleLine(ScheduleLine scheduleLine) {
    //     this.scheduleLines.add(scheduleLine);
    // }
    //
    // public void removeScheduleLine(ScheduleLine scheduleLine) {
    //     this.scheduleLines.remove(scheduleLine);
    // }

    @Override
    public String toString() {
        return "Day{'"+ fulldate +"'}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Day day = (Day) o;
        return fulldate.equals(day.fulldate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fulldate);
    }
}