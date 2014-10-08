/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.data.dao;
import java.util.*;


/**
 *
 * @author michal.lukac
 */
public interface IGenericDAO<T> {
    
    public void Insert(T t);
    
    public void Update(T t);
    
    public void Delete(T t);
    
    public boolean Find(T t);
}
