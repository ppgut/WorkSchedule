package com.data.employees;

import com.data.DAO;
import org.hibernate.SessionFactory;

import java.util.HashMap;
import java.util.Map;

public class EmployeeDAO extends DAO<Employee> {

    public EmployeeDAO(SessionFactory factory) {
        super(factory);
        setClazz(Employee.class);
    }

    @Override
    public Integer findId(Employee entity) {
        Map<String, Object> parametersToCompare = new HashMap<>();
        parametersToCompare.put(Employee_.FIRST_NAME, entity.getFirstName());
        parametersToCompare.put(Employee_.LAST_NAME, entity.getLastName());
        return super.findIdByProperties(entity, parametersToCompare);
    }
}
