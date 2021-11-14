package com.data.date;

import com.data.DAO;
import org.hibernate.SessionFactory;

public class DayDAO extends DAO<Day> {

    public DayDAO(SessionFactory factory) {
        super(factory);
        setClazz(Day.class);
    }

}

