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
 * @author michal.lukac
 */
public interface IGenericDAO<T> {
    
    public void setManager(EntityManager entityManager);
    
    public void Insert(T t);
    
    public void Update(T t);
    
    public void Delete(T t);
    
    public T Find(T t);
}
