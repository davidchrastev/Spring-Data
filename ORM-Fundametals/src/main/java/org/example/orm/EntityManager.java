package org.example.orm;

import org.example.orm.config.MyConnector;

public class EntityManager<E> implements DBContext<E> {

    private MyConnector connection;

    public EntityManager(MyConnector connection) {
        this.connection = connection;
    }
    @Override
    public boolean persist(Object entity) {
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
}
