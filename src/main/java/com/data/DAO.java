package com.data;

import com.google.common.base.Preconditions;
import org.hibernate.*;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Map;

public abstract class DAO<T> {
    private static SessionFactory factory;
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
            session = factory.openSession();
            tx = session.beginTransaction();
            ent = session.get(clazz, id);
            tx.commit();
            return ent;
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    /*
    To override in each entity DAO class using parameters typical for given entity,
    e.g. Employee -> firstName, lastName.
    FindByProperties() shall be used with parameter-value pairs mapped to 'propertiesToCompare' map.
    Based on these parameters predicates array is created to be used in criteria query.
    */
    public abstract Integer findId(T entity);

    public Integer findIdByProperties(T entity, Map<String, Object> propertiesToCompare) {
        List<T> results;
        Integer id = null;
        try {
            session = factory.openSession();
            tx = session.beginTransaction();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<T> cr = cb.createQuery(clazz);
            Root<T> root = cr.from(clazz);
            Predicate[] predicates = propertiesToCompare.entrySet().stream()
                    .map(e -> cb.equal(root.get(e.getKey()), e.getValue()))
                    .toArray(Predicate[]::new);
            cr.select(root).where(predicates);
            Query<T> query = session.createQuery(cr);
            results = query.getResultList();
            tx.commit();
            id = ((Entity) results.get(0)).getId();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } catch (IndexOutOfBoundsException e) {
            return null;
        } finally {
            session.close();
        }
        return id;
    }

    public List<T> findAll() {
        List<T> results;
        try {
            session = factory.openSession();
            tx = session.beginTransaction();
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
        } finally {
            session.close();
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
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
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
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return entity;
    }

    public void delete(final T entity) {
        Preconditions.checkNotNull(entity);
        try {
            session = factory.openSession();
            tx = session.beginTransaction();
            session.delete(session.get(clazz, ((Entity) entity).getId()));
            tx.commit();
            session.close();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void deleteById(final long entityId) {
        final T entity = findOne(entityId);
        Preconditions.checkState(entity != null);
        delete(entity);
    }
}
