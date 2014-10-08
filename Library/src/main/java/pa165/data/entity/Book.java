/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.data.entity;

import java.util.Set;

/**
 *
 * @author michal.lukac, 430614
 */
public class Book {
    
    public enum DepartmentEnum { Science, Hobby, Sport, Autobiografy, Religion}
    
    int IdBook;
    String Name;
    Set<String> Authors;
    String Description;
    String ISBN;
    Set<PrintedBook> Books;
    DepartmentEnum Department;
}
