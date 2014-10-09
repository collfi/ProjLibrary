/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.data.dao;

import java.util.List;
import java.util.Set;
import pa165.data.entity.Book;
import pa165.data.entity.Book.DepartmentEnum;

/**
 *
 * @author michal.lukac, 430614
 */
public interface IBookDAO {
    
    public List<Book> FindBooksByISBN(String Isbn);
    
    public List<Book> FindBooksByAuthor(String Author);
    
    public List<Book> FindBooksByDepartment(DepartmentEnum en);
    
    public List<Book> FindBooksByName(String Name);
    
}
