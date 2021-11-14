package com.data.employees;

import com.data.schedule.ScheduleLine;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Employee.class)
public abstract class Employee_ {

	public static volatile SingularAttribute<Employee, String> firstName;
	public static volatile SingularAttribute<Employee, String> lastName;
	public static volatile SingularAttribute<Employee, String> address;
	public static volatile SetAttribute<Employee, ScheduleLine> scheduleLines;
	public static volatile SingularAttribute<Employee, Integer> id;
	public static volatile SingularAttribute<Employee, String> position;
	public static volatile SingularAttribute<Employee, String> email;

	public static final String FIRST_NAME = "firstName";
	public static final String LAST_NAME = "lastName";
	public static final String ADDRESS = "address";
	public static final String SCHEDULE_LINES = "scheduleLines";
	public static final String ID = "id";
	public static final String POSITION = "position";
	public static final String EMAIL = "email";

}

