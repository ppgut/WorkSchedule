package com.app;

import com.data.date.*;
import com.data.employees.*;
import com.data.schedule.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class WorkSchedule {
    public static SessionFactory factory;
    public Session session;

    public static void main(String[] args) {
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

        // create DAO objects
        EmployeeDAO employeeDAO = new EmployeeDAO(factory);
        DayDAO dayDAO = new DayDAO(factory);
        ScheduleLineDAO scheduleLineDAO = new ScheduleLineDAO(factory);

        Employee emp = new Employee("Minion", "Two", PositionInCompany.TECHNICIAN);
        if (emp.getId() == null)
            System.out.println("null");
        else
            System.out.println(emp.getId());
        employeeDAO.saveOrUpdate(emp);
        System.out.println(emp.getId().toString());

        emp.setEmail("minion.two@minions.com");
        employeeDAO.saveOrUpdate(emp);
        System.out.println(emp.getId().toString());

        factory.getCurrentSession().close();
    }
}
