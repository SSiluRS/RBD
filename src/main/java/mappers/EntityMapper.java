package mappers;

import factory.MyFactory;
import jakarta.persistence.*;

import java.util.List;

public abstract class EntityMapper<T> {
    public void save(T entity) {
        MyFactory myFactory = MyFactory.getInstance();
        EntityManager entityManager = myFactory.getEntityManager();
        EntityTransaction transaction = myFactory.getTransaction();

        try {
            transaction.begin();
            entityManager.persist(entity);
            transaction.commit();
        } finally {
            if (transaction.isActive()){
                transaction.rollback();
            }
            entityManager.close();
        }
    }

    public void edit(T entity) {
        MyFactory myFactory = MyFactory.getInstance();
        EntityManager entityManager = myFactory.getEntityManager();
        EntityTransaction transaction = myFactory.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(entity);
            transaction.commit();
        } finally {
            if (transaction.isActive()){
                transaction.rollback();
            }
            entityManager.close();
        }
    }

    public void delete(T entity) {
        MyFactory myFactory = MyFactory.getInstance();
        EntityManager entityManager = myFactory.getEntityManager();
        EntityTransaction transaction = myFactory.getTransaction();
        try {
            transaction.begin();
            entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
            transaction.commit();
        } finally {
            if (transaction.isActive()){
                transaction.rollback();
            }
            entityManager.close();
        }
    }

    public List<T> findAll() {
        MyFactory myFactory = MyFactory.getInstance();
        EntityManager entityManager = myFactory.getEntityManager();
        TypedQuery <T> typedQuery = entityManager.createNamedQuery(getTableName()+".all", getType());
        List<T> list = typedQuery.getResultList();
        entityManager.close();
        return list;
    }

    protected List<T> findByParameter(String filter, Object field) {
        MyFactory myFactory = MyFactory.getInstance();
        EntityManager entityManager = myFactory.getEntityManager();
        TypedQuery <T> typedQuery = entityManager.createNamedQuery(getTableName()+filter, getType());
        typedQuery.setParameter(1, field);
        List<T> list = typedQuery.getResultList();
        entityManager.close();
        return list;
    }

    protected abstract Class<T> getType();

    protected abstract String getTableName();
}