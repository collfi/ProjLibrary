/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.dao;
import java.util.*;
import javax.persistence.EntityManager;


/**
 *
 * @author michal.lukac, xlukac, 430614
 */
public interface IGenericDAO<T> {
    //shoulnd't INSER AND UPDATE return T???????
    public void setManager(EntityManager entityManager);
    
    public void insert(T t);
    
    public void update(T t);
    
    public void delete(T t);

    //TODO why does it have to be only one T? maybe list here?
    public T find(T t);
}
