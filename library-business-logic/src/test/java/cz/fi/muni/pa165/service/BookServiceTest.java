package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.dao.BookDAOImpl;
import cz.fi.muni.pa165.entity.Book;
import cz.fi.muni.pa165.library.api.constants.Department;
import cz.fi.muni.pa165.library.api.dto.BookDTO;
import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import service.BookServiceImpl;
import service.DTOEntityManager;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.testng.Assert.assertEquals;


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
    private Book book2;
    private BookDTO bookdto;
    private BookDTO bookdto2;

    @BeforeMethod
    public void setUp() {
        mockBookService = new BookServiceImpl();
        mockBookDao = mock(BookDAOImpl.class);
        mockBookService.setBookDAO(mockBookDao);
        book = new Book();
        book.setIdBook(10l);
        book.setAuthors("Author1");
        book.setDepartment(Department.Sport);
        book.setDescription("Sports book about passion to run!");
        book.setISBN("123456789");
        book.setName("Born to run");
        bookdto = DTOEntityManager.bookEntitytoDTO(book);
        mockBookService.insertBook(bookdto);

        book2 = new Book();
        book2.setIdBook((9l));
        book2.setName("Harry Potter");
        book2.setDepartment(Department.Religion);
        book2.setAuthors("Author1");
        book2.setDescription("Story about young wizard with brave heart!");
        book2.setISBN("12341234");
    }

    @Test
    public void testFindBookById() {
        long id = 10l;
        when(mockBookDao.findBookById(id)).thenReturn(book);
        BookDTO result = mockBookService.findBookById(id);
        assertEquals(bookdto, result);
    }

    @Test
    public void testFindBooksByISBN() {
        List<Book> expected = new ArrayList<Book>();
        expected.add(book);
        when(mockBookDao.findBooksByISBN(bookdto.getISBN())).thenReturn(expected);
        List<BookDTO> result = mockBookService.findBooksByISBN(bookdto.getISBN());
        assertEquals(1, result.size());
    }

    @Test
    public void testFindBooksByName() {
        List<Book> expected = new ArrayList<Book>();
        expected.add(book);
        when(mockBookDao.findBooksByName(bookdto.getName())).thenReturn(expected);
        List<BookDTO> result = mockBookService.findBooksByName(bookdto.getName());
        assertEquals(expected.size(), result.size());
    }

    @Test
    public void testFindBooksByDepartment() {
        List<Book> expected = new ArrayList<Book>();
        expected.add(book);
        when(mockBookDao.findBooksByDepartment(Department.Sport)).thenReturn(expected);
        List<BookDTO> result = mockBookService.findBooksByDepartment(Department.Sport);
        assertEquals(1, result.size());
    }

    @Test
    public void testFindBooksByAuthor() {
        List<Book> expected = new ArrayList<Book>();
        expected.add(book);
        when(mockBookDao.findBooksByAuthor("Author1")).thenReturn(expected);
        List<BookDTO> result = mockBookService.findBooksByAuthor("Author1");
        assertEquals(1, result.size());
    }

    @Test
    public void insertBook() {
        List<Book> expected = new ArrayList<Book>();
        expected.add(book);
        expected.add(book2);

        doNothing().when(mockBookDao).insert(book2);

        bookdto2 = DTOEntityManager.bookEntitytoDTO(book2);

        mockBookService.insertBook(bookdto2);

        when(mockBookDao.findBooksByAuthor("Author1")).thenReturn(expected);
        List<BookDTO> result = mockBookService.findBooksByAuthor("Author1");
        assertEquals(2, result.size());
    }

    @Test
    public void updateBook() {
        List<Book> expected = new ArrayList<Book>();
        expected.add(book2);

        doNothing().when(mockBookDao).update(book2);

        bookdto2 = DTOEntityManager.bookEntitytoDTO(book2);
        bookdto2.setName("Author2");

        mockBookService.updateBook(bookdto2);

        when(mockBookDao.findBooksByAuthor("Author2")).thenReturn(expected);
        List<BookDTO> result = mockBookService.findBooksByAuthor("Author2");
        assertEquals(1, result.size());
    }

    @Test
    public void deleteBook() {
        List<Book> expected = new ArrayList<Book>();

        doNothing().when(mockBookDao).delete(book2);

        bookdto2 = DTOEntityManager.bookEntitytoDTO(book2);
        bookdto2.setName("Author2");

        mockBookService.deleteBook(bookdto2);

        when(mockBookDao.findBooksByAuthor("Author2")).thenReturn(expected);
        List<BookDTO> result = mockBookService.findBooksByAuthor("Author2");
        assertEquals(0, result.size());

    }
}
