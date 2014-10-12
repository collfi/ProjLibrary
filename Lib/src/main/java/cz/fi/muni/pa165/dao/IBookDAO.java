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
 * @author michal.lukac, xlukac, 430614
 */
public interface IBookDAO {
    
    /**
     * Find all books with specified ISBN number.
     * @param Isbn the unique number of book
     * @return List of books
     */
    public List<Book> FindBooksByISBN(String Isbn);
    
    /**
     * Find all books with name of the author
     * @param Author the name of author
     * @return List of books
     */
    public List<Book> FindBooksByAuthor(String Author);
    
    /**
     * Find all Books with specified Department
     * @param Department which belongs the book
     * @return List of books
     */
    public List<Book> FindBooksByDepartment(Department en);
    
    /**
     * Find all Books with specified name.
     * @param Name name of book
     * @return List of books
     */
    public List<Book> FindBooksByName(String Name);
    
}
