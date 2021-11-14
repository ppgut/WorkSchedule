package com.app;

import com.data.employees.Employee;
import com.data.employees.Employees;
import com.data.employees.PositionInCompany;

import java.time.LocalDate;

public class Test {
    public static void main(String[] args) {

        // check employees
        Employee emp1 = new Employee("Mark", "Twain", PositionInCompany.RECEPTIONIST);
        Employee emp2 = new Employee("Julian", "Tuwim", PositionInCompany.DOCTOR);

        System.out.println(java.sql.Date.valueOf(LocalDate.now()));
        System.out.println(java.sql.Date.valueOf(LocalDate.now()).toLocalDate().toString());

        Employees employees = new Employees();
        employees.addEmployee(emp1);
        employees.addEmployee(emp2);
        System.out.println(employees);
        employees.removeEmployee(emp2);
        System.out.println(employees);
        employees.removeEmployee(emp1);
        emp1 = null;
        emp2 = null;
        System.out.println(employees);
        Employee emp3 = new Employee("Jan", "Brzechwa", PositionInCompany.DOCTOR);
        employees.addEmployee(emp3);
        System.out.println(employees);
        System.out.println(employees.find("Jan", "Brzechwa"));
        System.out.println();
    }

}
