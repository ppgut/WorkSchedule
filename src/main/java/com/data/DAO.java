package com.data;

import com.data.date.Day;
import com.data.employees.Employee;
import com.data.schedule.ScheduleLine;
import com.google.common.base.Preconditions;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Map;

public abstract class DAO<T> {
    private SessionFactory factory;
    private Session session;
    private Transaction tx;
    private Class<T> clazz;

    public DAO() {}

    public DAO(SessionFactory factory) {this.factory = factory;}

    public final void setClazz(final Class<T> clazzToSet) {
        clazz = Preconditions.checkNotNull(clazzToSet);
    }

    public T findOne(final long id) {
        T ent;
        try {
            session = factory.getCurrentSession();
            tx = session.beginTransaction();
            ent = session.get(clazz, id);
            tx.commit();
            return ent;
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }
        return null;
    }

    public Integer findId(T entity, Map<String, Object> parameters) {
        List<T> results;
        Integer id = null;
        try {
            session = factory.getCurrentSession();
            tx = session.beginTransaction();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<T> cr = cb.createQuery(clazz);
            Root<T> root = cr.from(clazz);
            cr.select(root);
            if (clazz == Employee.class)
                cr.where(
                        cb.equal(root.get("firstName"), ((Employee) entity).getFirstName()),
                        cb.equal(root.get("lastName"), ((Employee) entity).getLastName()));
            else if (clazz == Day.class)
                cr.where(cb.equal(root.get("fulldate"), ((Day) entity).getFulldate()));
            else if (clazz == ScheduleLine.class)
                cr.where(
                        cb.equal(root.get("day"), ((ScheduleLine) entity).getDay()),
                        cb.equal(root.get("employee"), ((ScheduleLine) entity).getEmployee()));
            Query<T> query = session.createQuery(cr);
            results = query.getResultList();
            tx.commit();
            id = ((Entity) results.get(0)).getId();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("findId - IndexOutOfBoundException");
            return null;
        }
        return id;
    }

    public List<T> findAll() {
        List<T> results;
        try {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<T> cr = cb.createQuery(clazz);
            Root<T> root = cr.from(clazz);
            cr.select(root);
            Query<T> query = session.createQuery(cr);
            results = query.getResultList();
            tx.commit();
            return results;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return null;
    }

    public T saveOrUpdate(final T entity) {
        Preconditions.checkNotNull(entity);
        try {
            session = factory.openSession();
            tx = session.beginTransaction();
            session.saveOrUpdate(entity);
            tx.commit();
            session.close();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return entity;
    }

    public T update(final T entity) {
        Preconditions.checkNotNull(entity);
        try {
            session = factory.openSession();
            tx = session.beginTransaction();
            session.update(entity);
            tx.commit();
            session.close();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return entity;
    }

    public void delete(final T entity) {
        Preconditions.checkNotNull(entity);
        try {
            session = factory.openSession();
            tx = session.beginTransaction();
            session.delete(entity);
            tx.commit();
            session.close();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    public void deleteById(final long entityId) {
        final T entity = findOne(entityId);
        Preconditions.checkState(entity != null);
        delete(entity);
    }
}
