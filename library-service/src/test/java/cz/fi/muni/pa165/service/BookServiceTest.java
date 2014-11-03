/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.dao.BookDAOImpl;
import cz.fi.muni.pa165.dao.LoanDAOImpl;
import cz.fi.muni.pa165.dao.PrintedBookDAO;
import cz.fi.muni.pa165.dao.PrintedBookDAOImpl;
import cz.fi.muni.pa165.datatransferobject.BookDTO;
import cz.fi.muni.pa165.datatransferobject.LoanDTO;
import cz.fi.muni.pa165.datatransferobject.PrintedBookDTO;
import cz.fi.muni.pa165.entity.Book;
import cz.fi.muni.pa165.entity.Book.Department;
import cz.fi.muni.pa165.entity.Loan;
import cz.fi.muni.pa165.entity.PrintedBook;
import cz.fi.muni.pa165.service.api.BookService;
import java.util.ArrayList;
import java.util.List;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.BeforeMethod;

import static org.testng.Assert.assertNull;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
 

/**
 * This is the Mock test class for BookService
 * 
 * @author michal.lukac, 430614
 */
public class BookServiceTest {

    private BookServiceImpl mockBookService;
    @Mock
    private BookDAOImpl mockBookDao;

    private Book book;
    private BookDTO bookdto;
    
    @BeforeMethod
    public void setUp(){
        mockBookService = new BookServiceImpl();
        mockBookDao = mock(BookDAOImpl.class);
        mockBookService.setBookDAO(mockBookDao);
        book = new Book();
        book.setIdBook(10l);
        book.setAuthors("Author1");
        book.setDapertment(Book.Department.Sport);
        book.setDescription("Sports book about passion to run!");
        book.setISBN("123456789");
        book.setName("Born to run");
        bookdto = DTOEntityManager.bookEntitytoDTO(book);
        mockBookService.insertBook(bookdto);
    }
    
    @Test
    public void testFindBookById(){
        long id = 10l;
        when(mockBookDao.findBookById(id)).thenReturn(book);
        BookDTO result = mockBookService.findBookById(id);
        assertEquals(bookdto, result);
    }
    
    @Test
    public void testFindBooksByISBN(){
        List<Book> expected =  new ArrayList<Book>();
        expected.add(book);
        when(mockBookDao.findBooksByISBN(bookdto.getISBN())).thenReturn(expected);
        List<BookDTO> result = mockBookService.findBooksByISBN(bookdto.getISBN());
        assertEquals(expected.size(), result.size());
    }

    @Test
    public void testFindBooksByName(){
        List<Book> expected =  new ArrayList<Book>();
        expected.add(book);
        when(mockBookDao.findBooksByName(bookdto.getName())).thenReturn(expected);
        List<BookDTO> result = mockBookService.findBooksByName(bookdto.getName());
        assertEquals(expected.size(), result.size());
    }

    @Test
    public void testFindBooksByDepartment(){
        List<Book> expected =  new ArrayList<Book>();
        expected.add(book);
        when(mockBookDao.findBooksByDepartment(Department.Sport)).thenReturn(expected);
        List<BookDTO> result = mockBookService.findBooksByDepartment(Department.Sport);
        assertEquals(expected.size(), result.size());
    }

    @Test
    public void testFindBooksByAuthor(){
        List<Book> expected =  new ArrayList<Book>();
        expected.add(book);
        when(mockBookDao.findBooksByAuthor("Author1")).thenReturn(expected);
        List<BookDTO> result = mockBookService.findBooksByAuthor("Author1");
        assertEquals(expected.size(), result.size());
    }
    
    public void insertBook()
    {
        
    }
    
    public void updateBook()
    {
        
    }
    
    public void deleteBook()
    {
        
    }
}
