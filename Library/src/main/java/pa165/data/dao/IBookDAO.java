/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.data.dao;

import java.util.Set;
import pa165.data.entity.Book;
import pa165.data.entity.Book.DepartmentEnum;

/**
 *
 * @author michal.lukac, 430614
 */
public interface IBookDAO {
    
    public Set<Book> FindBooksByISBN(String Isbn);
    
    public Set<Book> FindBooksByAuthor(String Author);
    
    public Set<Book> FindBooksByDepartment(String Isbn, DepartmentEnum en);
    
    public Set<Book> FindBooksByName(String Name);
    
}
