package com.data.employees;

import com.data.DAO;
import org.hibernate.SessionFactory;

public class EmployeeDAO extends DAO<Employee> {

    public EmployeeDAO(SessionFactory factory) {
        super(factory);
        setClazz(Employee.class);
    }

}
