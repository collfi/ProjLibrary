/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.dao;

import javax.persistence.EntityManager;


/**
 * Generic DAO which should extend every DAO interface.
 *
 * @author michal.lukac, xlukac, 430614
 */
public interface GenericDAO<T> {

    /**
     * Sets the entity manager.
     *
     * @param entityManager
     */
    public void setManager(EntityManager entityManager);

    /**
     * Insert T to the database.
     *
     * @param t Insert to to the database.
     */
    public void insert(T t);

    /**
     * Update T in database.
     *
     * @param t Update of the object.
     */
    public void update(T t);

    /**
     * Delete T from database.
     *
     * @param t object which we want to delete.
     */
    public void delete(T t);

    /**
     * Find t in database
     *
     * @param t object which we want to find.
     * @return if find return T
     */
    public T find(T t);

    //TODO what about find by id here?
}
