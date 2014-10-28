/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.datatransferobject.BookDTO;
import cz.fi.muni.pa165.service.api.BookService;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
 

/**
 * This is the Mock test class for BookService
 * 
 * @author michal.lukac, 430614
 */
public class BookServiceTest {

    private static BookServiceImpl mockBookService;
    private static BookDTO book1;
    private static BookDTO book2;
    
    @BeforeClass
    public static void setUp(){
        mockBookService = mock(BookServiceImpl.class);
    }
    
    @Test
    public static void testInsert(){
        mockBookService.insertPrintedBook(book1);
        //Book myBook = mockedBookDAL.getBook(isbn);
        //assertNotNull(myBook);

    }
}
