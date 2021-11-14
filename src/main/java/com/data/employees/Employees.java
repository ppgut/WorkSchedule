package com.data.employees;

import java.util.ArrayList;
import java.util.List;

public class Employees {

    private List<Employee> employeeList = new ArrayList<>();
    private static long lastEmployeeId;
    public void addEmployee(Employee employee) {
        employeeList.add(employee);
    }

    public void removeEmployee(Employee employee) {
        employeeList.remove(employee);
    }

    public Employee find(String firstName, String lastName) throws NullPointerException {
        for (Employee e : employeeList) {
            if (e.getFirstName().equals(firstName) && e.getLastName().equals(lastName))
                return e;
        }
        throw new NullPointerException();
    }

    public Employee find(int employeeId) throws NullPointerException {
        for (Employee e : employeeList) {
            if (e.getId() == employeeId)
                return e;
        }
        throw new NullPointerException();
    }

    public int count() {
        return employeeList.size();
    }

    private void refreshLastEmployeeId() {
        for (Employee e : employeeList) {
            if (e.getId() > lastEmployeeId)
                lastEmployeeId = e.getId();
        }
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Employees{\n");
        for (Employee e: employeeList) {
            sb.append(e).append("\n");
        }
        return sb.deleteCharAt(sb.length()-1).append("}").toString();
    }
}
