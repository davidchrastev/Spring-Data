package org.example.orm;

public interface DBContext<E> {

    boolean persist(E entity);
    Iterable<E> find(Class<E> table);
    Iterable<E> find(Class<E> table, String where);
    E findFirst(Class<E> table);
    E findFirst(Class<E> table, String where);

}