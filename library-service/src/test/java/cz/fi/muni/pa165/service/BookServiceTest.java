/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.datatransferobject.BookDTO;
import cz.fi.muni.pa165.service.api.BookService;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.testng.annotations.BeforeMethod;
 

/**
 * This is the Mock test class for BookService
 * 
 * @author michal.lukac, 430614
 */
public class BookServiceTest {

    private static BookServiceImpl mockBookService;
    private BookDTO book1;
    private BookDTO book2;
    
    @BeforeMethod
    public void setUp(){
        mockBookService = mock(BookServiceImpl.class);
        book1 = new BookDTO();
        book1.setIdBook(1);
        book1.setISBN("123");
    }
    
    @Test
    public void testInsert(){
        mockBookService.insertBook(book1);
        BookDTO myBook = mockBookService.findBook(book1);
        assertNotNull(myBook);

    }
}
