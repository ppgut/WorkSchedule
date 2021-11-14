package com.data.schedule;


import com.data.DAO;
import com.data.employees.Employee;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ScheduleLineDAO extends DAO<ScheduleLine> {

    public ScheduleLineDAO(SessionFactory factory) {
        super(factory);
        setClazz(ScheduleLine.class);
    }

}
