package com.data.date;

import com.data.DAO;
import org.hibernate.SessionFactory;

import java.util.HashMap;
import java.util.Map;

public class DayDAO extends DAO<Day> {

    public DayDAO(SessionFactory factory) {
        super(factory);
        setClazz(Day.class);
    }

    @Override
    public Integer findId(Day entity) {
        Map<String, Object> parametersToCompare = new HashMap<>();
        parametersToCompare.put(Day_.FULLDATE, entity.getFulldate());
        return super.findIdByProperties(entity, parametersToCompare);
    }
}

