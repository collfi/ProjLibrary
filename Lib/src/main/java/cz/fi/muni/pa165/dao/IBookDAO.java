/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.dao;

import java.util.List;
import java.util.Set;
import cz.fi.muni.pa165.entity.Book;
import cz.fi.muni.pa165.entity.Book.Department;

/**
 *
 * @author michal.lukac, 430614
 */
public interface IBookDAO {
    
    public List<Book> FindBooksByISBN(String Isbn);
    
    public List<Book> FindBooksByAuthor(String Author);
    
    public List<Book> FindBooksByDepartment(Department en);
    
    public List<Book> FindBooksByName(String Name);
    
}
