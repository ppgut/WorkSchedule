package com.data.schedule;


import com.data.DAO;
import org.hibernate.SessionFactory;

import java.util.HashMap;
import java.util.Map;

public class ScheduleLineDAO extends DAO<ScheduleLine> {

    public ScheduleLineDAO(SessionFactory factory) {
        super(factory);
        setClazz(ScheduleLine.class);
    }

    @Override
    public Integer findId(ScheduleLine entity) {
        Map<String, Object> parametersToCompare = new HashMap<>();
        parametersToCompare.put(ScheduleLine_.DAY, entity.getDay().getId());
        parametersToCompare.put(ScheduleLine_.EMPLOYEE, entity.getEmployee().getId());
        return super.findIdByProperties(entity, parametersToCompare);
    }
}
