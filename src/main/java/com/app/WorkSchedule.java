package com.app;

import com.data.date.Day;
import com.data.date.DayDAO;
import com.data.employees.Employee;
import com.data.employees.EmployeeDAO;
import com.data.employees.PositionInCompany;
import com.data.schedule.ScheduleLine;
import com.data.schedule.ScheduleLineDAO;
import com.data.shifts.BaseShift;
import com.data.shifts.Shift;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;

public class WorkSchedule {
    public static SessionFactory factory;
    public static EmployeeDAO employeeDAO;
    public static DayDAO dayDAO;
    public static ScheduleLineDAO scheduleLineDAO;

    public static void main(String[] args) {    // ------------------------------------------

        initializeStaticObjects();
        // loadTestData();

        // Day dayToUpdate = new Day(LocalDate.of(2021, 11, 11));
        // dayToUpdate.setIsHoliday(true);
        // dayDAO.update(dayToUpdate);
        //
        // Day dayToDelete = new Day(LocalDate.of(2021, 11, 10));
        // dayDAO.delete(dayToDelete); // cascade removes the lines scheduled for this day (one-to-many relationship)

        Day day1 = new Day(LocalDate.of(2022, 2, 22));
        Employee emp1 = new Employee("Miss", "Moneypenny", PositionInCompany.RECEPTIONIST);
        emp1.setEmail("money.penny@mi6.com");
        ScheduleLine scheduleLine = new ScheduleLine(day1, emp1, new Shift(BaseShift.DAY));
        scheduleLineDAO.saveOrUpdate(scheduleLine); // cascade save/updates Day and Employee entities

    } // -------------------------------------------------------------------------------------


    private static void initializeStaticObjects() {
        try {
            factory = new Configuration().configure()
                    .addAnnotatedClass(ScheduleLine.class)
                    .addAnnotatedClass(Employee.class)
                    .addAnnotatedClass(Day.class)
                    .buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object " + ex);
            throw new RuntimeException(ex);
        }
        employeeDAO = new EmployeeDAO(factory);
        dayDAO = new DayDAO(factory);
        scheduleLineDAO = new ScheduleLineDAO(factory);
    }

    private static void loadTestData() {

        // save this month days into database
        LocalDate today = LocalDate.now();
        int year = today.getYear();
        int month = today.getMonthValue();
        LocalDate dayOfThisMonth = LocalDate.of(year, month, 1);
        LocalDate firstDayOfNextMonth = dayOfThisMonth.plusMonths(1);
        while (dayOfThisMonth.isBefore(firstDayOfNextMonth)) {
            dayDAO.saveOrUpdate(new Day(dayOfThisMonth));
            dayOfThisMonth = dayOfThisMonth.plusDays(1);
        }

        // create some employees
        Employee emp1 = new Employee("John", "Watson", PositionInCompany.DOCTOR);
        Employee emp2 = new Employee("Henry", "Jekyll", PositionInCompany.DOCTOR);
        Employee emp3 = new Employee("Doctor", "Proctor", PositionInCompany.DOCTOR);
        Employee emp4 = new Employee("Minion", "One", PositionInCompany.TECHNICIAN);
        Employee emp5 = new Employee("Minion", "Two", PositionInCompany.TECHNICIAN);
        employeeDAO.saveOrUpdate(emp1);
        employeeDAO.saveOrUpdate(emp2);
        employeeDAO.saveOrUpdate(emp3);
        employeeDAO.saveOrUpdate(emp4);
        employeeDAO.saveOrUpdate(emp5);

        // schedule some shifts
        Day day10 = new Day(LocalDate.of(year, month, 10));
        Day day11 = new Day(LocalDate.of(year, month, 11));
        Shift morningShift = new Shift(BaseShift.MORNING);
        Shift afternoonShift = new Shift(BaseShift.AFTERNOON);
        ScheduleLine scheduleLine1 = new ScheduleLine(day10, emp1, morningShift);
        ScheduleLine scheduleLine2 = new ScheduleLine(day10, emp2, morningShift);
        ScheduleLine scheduleLine3 = new ScheduleLine(day10, emp3, afternoonShift);
        ScheduleLine scheduleLine4 = new ScheduleLine(day11, emp4, morningShift);
        ScheduleLine scheduleLine5 = new ScheduleLine(day11, emp5, afternoonShift);
        scheduleLineDAO.saveOrUpdate(scheduleLine1);
        scheduleLineDAO.saveOrUpdate(scheduleLine2);
        scheduleLineDAO.saveOrUpdate(scheduleLine3);
        scheduleLineDAO.saveOrUpdate(scheduleLine4);
        scheduleLineDAO.saveOrUpdate(scheduleLine5);
    }
}
