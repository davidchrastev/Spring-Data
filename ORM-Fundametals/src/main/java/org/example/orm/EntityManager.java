package org.example.orm;

import org.example.orm.anotations.Id;
import org.example.orm.config.MyConnector;

import java.lang.reflect.Field;

public class EntityManager<E> implements DBContext<E> {

    private MyConnector connection;

    public EntityManager(MyConnector connection) {
        this.connection = connection;
    }
    @Override
    public boolean persist(Object entity) throws IllegalAccessException {
        Field idField = getIdField(entity.getClass());
        idField.setAccessible(true);
        Object idValue = idField.get(entity);

        if (idValue == null || (long) idValue == 0) {
//            return isInsertEntity(entity);
        }

        return false;
    }

    @Override
    public Iterable find(Class table) {
        return null;
    }

    @Override
    public Iterable find(Class table, String where) {
        return null;
    }

    @Override
    public Object findFirst(Class table) {
        return null;
    }

    @Override
    public Object findFirst(Class table, String where) {
        return null;
    }

    private Field getIdField(Class<?> entityClass) {
        Field[] declaredFields =
                entityClass.getDeclaredFields();

        for (Field declaredField : declaredFields) {
            if (declaredField.isAnnotationPresent(Id.class)) {
                return declaredField;
            }
        }

        throw new UnsupportedOperationException("Entity does not exist");
    }

    private boolean isInsertEntity(E entity) {
        return false;
    }
}
