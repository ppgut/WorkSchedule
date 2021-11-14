package com.data.date;

import com.data.schedule.ScheduleLine;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Day.class)
public abstract class Day_ {

	public static volatile SingularAttribute<Day, String> fulldate;
	public static volatile SingularAttribute<Day, Integer> dayOfWeek;
	public static volatile SingularAttribute<Day, Integer> month;
	public static volatile SingularAttribute<Day, Integer> year;
	public static volatile SingularAttribute<Day, Integer> dayOfMonth;
	public static volatile SingularAttribute<Day, Boolean> isHoliday;
	public static volatile SetAttribute<Day, ScheduleLine> scheduleLines;
	public static volatile SingularAttribute<Day, Integer> id;
	public static volatile SingularAttribute<Day, Boolean> isWeekend;

	public static final String FULLDATE = "fulldate";
	public static final String DAY_OF_WEEK = "dayOfWeek";
	public static final String MONTH = "month";
	public static final String YEAR = "year";
	public static final String DAY_OF_MONTH = "dayOfMonth";
	public static final String IS_HOLIDAY = "isHoliday";
	public static final String SCHEDULE_LINES = "scheduleLines";
	public static final String ID = "id";
	public static final String IS_WEEKEND = "isWeekend";

}

