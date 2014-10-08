/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.data.services;

import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import pa165.data.entity.Book;

/**
 *
 * @author 
 */
public class BookDAOTest {
    
    public BookDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of Insert method, of class BookDAO.
     */
    @org.junit.Test
    public void testInsert() {
        System.out.println("Insert");
        Book t = null;
        BookDAO instance = new BookDAO();
        instance.Insert(t);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of Update method, of class BookDAO.
     */
    @org.junit.Test
    public void testUpdate() {
        System.out.println("Update");
        Book t = null;
        BookDAO instance = new BookDAO();
        instance.Update(t);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of Delete method, of class BookDAO.
     */
    @org.junit.Test
    public void testDelete() {
        System.out.println("Delete");
        Book t = null;
        BookDAO instance = new BookDAO();
        instance.Delete(t);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of Find method, of class BookDAO.
     */
    @org.junit.Test
    public void testFind() {
        System.out.println("Find");
        Book t = null;
        BookDAO instance = new BookDAO();
        boolean expResult = false;
        boolean result = instance.Find(t);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of FindBooksByISBN method, of class BookDAO.
     */
    @org.junit.Test
    public void testFindBooksByISBN() {
        System.out.println("FindBooksByISBN");
        String Isbn = "";
        BookDAO instance = new BookDAO();
        Set<Book> expResult = null;
        Set<Book> result = instance.FindBooksByISBN(Isbn);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of FindBooksByAuthor method, of class BookDAO.
     */
    @org.junit.Test
    public void testFindBooksByAuthor() {
        System.out.println("FindBooksByAuthor");
        String Author = "";
        BookDAO instance = new BookDAO();
        Set<Book> expResult = null;
        Set<Book> result = instance.FindBooksByAuthor(Author);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of FindBooksByDepartment method, of class BookDAO.
     */
    @org.junit.Test
    public void testFindBooksByDepartment() {
        System.out.println("FindBooksByDepartment");
        String Isbn = "";
        Book.DepartmentEnum en = null;
        BookDAO instance = new BookDAO();
        Set<Book> expResult = null;
        Set<Book> result = instance.FindBooksByDepartment(Isbn, en);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of FindBooksByName method, of class BookDAO.
     */
    @org.junit.Test
    public void testFindBooksByName() {
        System.out.println("FindBooksByName");
        String Name = "";
        BookDAO instance = new BookDAO();
        Set<Book> expResult = null;
        Set<Book> result = instance.FindBooksByName(Name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
