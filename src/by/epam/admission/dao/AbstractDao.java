/*
 * class: AbstractDao
 */

package by.epam.admission.dao;

import by.epam.admission.exception.ProjectException;
import by.epam.admission.model.Entity;
import by.epam.admission.pool.ProxyConnection;

/**
 * @author Maxim Burishinets
 * @version 1.0 20 Aug 2018
 */
public abstract class AbstractDao<K, T extends Entity> {

    protected ProxyConnection connection;

//    public abstract List<T> findAll() throws ProjectException;
    
    public abstract T findEntityById(K id) throws ProjectException;
    
    public abstract boolean delete(K id) throws ProjectException;

    public abstract boolean create(T entity) throws ProjectException;
    
    public abstract boolean update(T entity) throws ProjectException;

    void setConnection(ProxyConnection connection) {
        this.connection = connection;
    }
}
