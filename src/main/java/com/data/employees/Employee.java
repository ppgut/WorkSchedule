package com.data.employees;

import com.data.schedule.*;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "employee")
public class Employee extends com.data.Entity {

    // TODO design employee id management
    static int lastEmployeeId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    // @NaturalId
    @Column(name = "first_name", length = 50)
    private String firstName;

    // @NaturalId
    @Column(name = "last_name", length = 50)
    private String lastName;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "address", length = 50)
    private String address;

    @Column(name = "position", length = 50)
    private String position;

    // @OneToMany(mappedBy = "employee")
    // private Set<ScheduleLine> scheduleLines = new HashSet<>();

    public Employee() {}

    public Employee(String firstName, String lastName) {
        // this.id = ++lastEmployeeId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Employee(String firstName, String lastName, PositionInCompany position) {
        // this.id = ++lastEmployeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position.toString();
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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
        return "Employee{" +
                "id=" + id + ", '" + firstName + ' ' + lastName + "', " + position + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        System.out.println("equals: " + (firstName.equals(employee.firstName) && lastName.equals(employee.lastName)));
        return firstName.equals(employee.firstName) && lastName.equals(employee.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }
}