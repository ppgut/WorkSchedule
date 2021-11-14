package com.data.schedule;

import com.data.date.Day;
import com.data.employees.Employee;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ScheduleLine.class)
public abstract class ScheduleLine_ {

	public static volatile SingularAttribute<ScheduleLine, Integer> startOffset;
	public static volatile SingularAttribute<ScheduleLine, Integer> shiftExtension;
	public static volatile SingularAttribute<ScheduleLine, Integer> id;
	public static volatile SingularAttribute<ScheduleLine, Employee> employee;
	public static volatile SingularAttribute<ScheduleLine, String> baseShift;
	public static volatile SingularAttribute<ScheduleLine, Day> day;

	public static final String START_OFFSET = "startOffset";
	public static final String SHIFT_EXTENSION = "shiftExtension";
	public static final String ID = "id";
	public static final String EMPLOYEE = "employee";
	public static final String BASE_SHIFT = "baseShift";
	public static final String DAY = "day";

}

