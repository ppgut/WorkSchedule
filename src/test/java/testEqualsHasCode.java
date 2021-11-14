import com.data.Entity;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import javax.persistence.EntityManagerFactory;
import java.util.HashSet;
import java.util.Set;

import static org.hibernate.testing.transaction.TransactionUtil.*;
import static org.junit.jupiter.api.Assertions.*;

public class testEqualsHasCode<T extends Entity> extends JpaBaseRolledBackTestCase {

    // TODO figure out how to run this test including JpaBaseRolledBackTestCase class

    private EntityManagerFactory entityManagerFactory() {
        return entityManagerFactory;
    }

    @Test
    protected void assertEqualityConsistency(Class<T> clazz, T entity) {

        Set<T> tuples = new HashSet<T>();

        assertFalse(tuples.contains(entity));
        tuples.add(entity);
        assertTrue(tuples.contains(entity));

        doInJPA(this::entityManagerFactory, entityManager -> {
                entityManager.persist(entity);
                entityManager.flush();
                assertTrue(tuples.contains(entity),
                "The entity is not found in the Set after it's persisted.");
        });

        assertTrue(tuples.contains(entity));

        doInJPA(this::entityManagerFactory, entityManager -> {
            T entityProxy = entityManager.getReference(
                    clazz,
                    entity.getId()
            );
            assertEquals(entityProxy, entity,
                    "The entity proxy is not equal with the entity.");
        });

        doInJPA(this::entityManagerFactory, entityManager -> {
            T entityProxy = entityManager.getReference(
                    clazz,
                    entity.getId()
            );
            assertEquals(entity, entityProxy,
                    "The entity is not equal with the entity proxy.");
        });

        doInJPA(this::entityManagerFactory, entityManager -> {
            T _entity = entityManager.merge(entity);
            assertTrue(tuples.contains(_entity),
                    "The entity is not found in the Set after it's merged.");
        });

        doInJPA(this::entityManagerFactory, entityManager -> {
            entityManager.unwrap(Session.class).update(entity);
            assertTrue(tuples.contains(entity),
                    "The entity is not found in the Set after it's reattached.");
        });

        doInJPA(this::entityManagerFactory, entityManager -> {
            T _entity = entityManager.find(clazz, entity.getId());
            assertTrue(tuples.contains(_entity),
                    "The entity is not found in the Set after it's loaded " +
                            "in a different Persistence Context.");
        });

        doInJPA(this::entityManagerFactory, entityManager -> {
            T _entity = entityManager.getReference(clazz, entity.getId());
            assertTrue(tuples.contains(_entity),
                    "The entity is not found in the Set after it's loaded " +
                            "as a proxy in a different Persistence Context.");
        });

        T deletedEntity = doInJPA(this::entityManagerFactory, entityManager -> {
            T _entity = entityManager.getReference(
                    clazz,
                    entity.getId()
            );
            entityManager.remove(_entity);
            return _entity;
        });

        assertTrue(tuples.contains(deletedEntity),
                "The entity is not found in the Set even after it's deleted.");
    }
}
