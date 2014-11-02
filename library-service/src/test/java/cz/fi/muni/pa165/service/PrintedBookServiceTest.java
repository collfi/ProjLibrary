/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.dao.BookDAO;
import cz.fi.muni.pa165.dao.BookDAOImpl;
import cz.fi.muni.pa165.dao.LoanDAOImpl;
import cz.fi.muni.pa165.dao.PrintedBookDAO;
import cz.fi.muni.pa165.dao.PrintedBookDAOImpl;
import cz.fi.muni.pa165.datatransferobject.BookDTO;
import cz.fi.muni.pa165.datatransferobject.LoanDTO;
import cz.fi.muni.pa165.datatransferobject.PrintedBookDTO;
import cz.fi.muni.pa165.entity.Book;
import cz.fi.muni.pa165.entity.Loan;
import cz.fi.muni.pa165.entity.PrintedBook;
import cz.fi.muni.pa165.service.api.BookService;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;

import static org.testng.Assert.*;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Boris Valentovic - xvalent2
 */
public class PrintedBookServiceTest {

    private PrintedBookServiceImpl pbookService;
    private PrintedBookDAOImpl mockpBookDao;
    private BookDAOImpl mockBookDao;
    private LoanDAOImpl mockLoanDao;

    private PrintedBookDTO pbookdto1;
    private PrintedBookDTO pbookdto2;
    private PrintedBook pbook1;
    private PrintedBook pbook2;
    private Book book;
    private BookDTO bookdto;
    private Loan loan;
    private LoanDTO loandto;

    @BeforeMethod
    public void setUp() {

        pbookService = new PrintedBookServiceImpl();
        mockpBookDao = mock(PrintedBookDAOImpl.class);
        mockBookDao = mock(BookDAOImpl.class);
        mockLoanDao = mock(LoanDAOImpl.class);
        pbookService.setPrintedBookDao(mockpBookDao);
        pbookService.setBookDao(mockBookDao);
        pbookService.setLoanDao(mockLoanDao);

        book = new Book();
        book.setIdBook(10l);
        bookdto = DTOEntityManager.bookEntitytoDTO(book);

        loan = new Loan();
        loan.setIdLoan(20);
        loandto = DTOEntityManager.loanEntitytoDTO(loan);

        pbook1 = new PrintedBook(book, Boolean.TRUE, PrintedBook.Condition.Used, loan);
        pbook2 = new PrintedBook(book, Boolean.FALSE, PrintedBook.Condition.New, null);

        pbookdto1 = DTOEntityManager.printedBookEntitytoDTO(pbook1);
        pbookdto2 = DTOEntityManager.printedBookEntitytoDTO(pbook2);

    }

    @Test
    public void findPBByIdTest() {
        Long id = 11l;
        pbook1.setIdPrintedBook(id);
        pbookdto1.setIdPrintedBook(id);
        when(mockpBookDao.findPrintedBookById(id)).thenReturn(pbook1);
        PrintedBookDTO result = pbookService.findPrintedBookById(id);
        //System.out.println(pbookdto1.getIdPrintedBook() + "------------" + result.getIdPrintedBook());
        assertEquals(pbookdto1, result);
    }

    /* @Test
     public void findPBByBookTest() {
     List<PrintedBookDTO> expDTO = new ArrayList<>();
     List<PrintedBook> res = new ArrayList<>();
     pbook1.setIdPrintedBook(11l);
     pbook1.setIdPrintedBook(12l);
     pbookdto1.setIdPrintedBook(11l);
     pbookdto2.setIdPrintedBook(12l);

     expDTO.add(pbookdto1);
     expDTO.add(pbookdto2);

     for (PrintedBookDTO pb : expDTO) {
     res.add(DTOEntityManager.printedBookDTOtoEntity(pb));
     }
     when(mockpBookDao.findPrintedBooks(book)).thenReturn(res);
     when(mockBookDao.findBookById(bookdto.getIdBook())).thenReturn(book);

     List<PrintedBookDTO> resDTO = pbookService.findPrintedBooksByBook(bookdto);

     assertEquals(resDTO, expDTO);

     }*/
    @Test
    public void insertTest() {
        doThrow(new RuntimeException()).when(mockpBookDao).insert(pbook1);
        try {
            pbookService.insertPrintedBook(pbookdto1);
        } catch (RuntimeException re) {
        }
    }

    @Test
    public void updateTest() {
        doThrow(new RuntimeException()).when(mockpBookDao).update(pbook1);
        try {
            pbookService.updatePrintedBook(pbookdto1);
        } catch (RuntimeException re) {
        }
    }

    @Test
    public void deleteTest() {
        doThrow(new RuntimeException()).when(mockpBookDao).delete(pbook1);
        try {
            pbookService.deletePrintedBook(pbookdto1);
        } catch (RuntimeException re) {
        }
    }

    @Test
    public void findPrintetBookTest() {
        /* when(mockpBookDao.find(null)).thenThrow(new IllegalArgumentException());
         try {
         PrintedBookDTO result = pbookService.findPrintedBook(null);
         fail("Null bookTO passed. Should throw Exception");
         } catch (IllegalArgumentException ex) {
         }*/

        pbook1.setIdPrintedBook(11l);
        pbookdto1.setIdPrintedBook(11l);
        when(mockpBookDao.find(pbook1)).thenReturn(pbook1);
        PrintedBookDTO result = pbookService.findPrintedBook(pbookdto1);
        //System.out.println(pbookdto1.getIdPrintedBook() + "------------" + result.getIdPrintedBook());
        assertEquals(pbookdto1, result);

    }

    @Test
    public void findPBByBookTest() {

        /*when(mockpBookDao.findPrintedBooks(null)).thenThrow(new IllegalArgumentException());
         try {
         List<PrintedBookDTO> result = pbookService.findPrintedBooksByBook(null);
         fail("Null bookTO passed. Should throw Exception");
         } catch (IllegalArgumentException ex) {
         }*/
        List<PrintedBookDTO> expDTO = new ArrayList<>();
        List<PrintedBook> res = new ArrayList<>();
        pbook1.setIdPrintedBook(11l);
        pbook1.setIdPrintedBook(12l);
        pbookdto1.setIdPrintedBook(11l);
        pbookdto2.setIdPrintedBook(12l);

        expDTO.add(pbookdto1);
        expDTO.add(pbookdto2);

        for (PrintedBookDTO pb : expDTO) {
            res.add(DTOEntityManager.printedBookDTOtoEntity(pb));
        }
        when(mockpBookDao.findPrintedBooks(book)).thenReturn(res);
        when(mockBookDao.find(book)).thenReturn(book);

        List<PrintedBookDTO> resDTO = pbookService.findPrintedBooksByBook(bookdto);

        assertEquals(resDTO, expDTO);

    }

    @Test
    public void finPBByStateTest() {
        List<PrintedBookDTO> expDTO = new ArrayList<>();
        List<PrintedBook> res = new ArrayList<>();
        pbook1.setIdPrintedBook(11l);
        pbook1.setIdPrintedBook(12l);
        pbookdto1.setIdPrintedBook(11l);
        pbookdto2.setIdPrintedBook(12l);

        expDTO.add(pbookdto1);
        res.add(DTOEntityManager.printedBookDTOtoEntity(pbookdto1));

        when(mockpBookDao.findPrintedBooksByState(book, Boolean.TRUE)).thenReturn(res);
        when(mockBookDao.findBookById(bookdto.getIdBook())).thenReturn(book);

        List<PrintedBookDTO> resDTO = pbookService.findPrintedBookByState(bookdto, Boolean.TRUE);
        assertEquals(resDTO, expDTO);

    }

    @Test
    public void findAllBorrowedPBTest() {
        List<PrintedBookDTO> expDTO = new ArrayList<>();
        List<PrintedBook> res = new ArrayList<>();
        pbook1.setIdPrintedBook(11l);
        pbook1.setIdPrintedBook(12l);
        pbookdto1.setIdPrintedBook(11l);
        pbookdto2.setIdPrintedBook(12l);

        expDTO.add(pbookdto1);
        res.add(DTOEntityManager.printedBookDTOtoEntity(pbookdto1));

        when(mockpBookDao.findAllBorrowedPrintedBooks()).thenReturn(res);
        when(mockBookDao.findBookById(bookdto.getIdBook())).thenReturn(book);

        List<PrintedBookDTO> resDTO = pbookService.findAllBorrowedPrintedBooks();
        assertEquals(resDTO, expDTO);
    }

    @Test
    public void findAllPBByLoanTest() {
        List<PrintedBookDTO> expDTO = new ArrayList<>();
        List<PrintedBook> res = new ArrayList<>();
        pbook1.setIdPrintedBook(11l);
        pbook1.setIdPrintedBook(12l);
        pbookdto1.setIdPrintedBook(11l);
        pbookdto2.setIdPrintedBook(12l);

        expDTO.add(pbookdto1);
        expDTO.add(pbookdto2);

        for (PrintedBookDTO pb : expDTO) {
            res.add(DTOEntityManager.printedBookDTOtoEntity(pb));
        }
        when(mockpBookDao.findAllPrintedBooksByLoan(loan)).thenReturn(res);
        when(mockLoanDao.find(loan)).thenReturn(loan);

        List<PrintedBookDTO> resDTO = pbookService.findAllPrintedBooksByLoan(loandto);

        assertEquals(resDTO, expDTO);
    }

    @Test
    public void findPBByLoanTest() {
        List<PrintedBookDTO> expDTO = new ArrayList<>();
        List<PrintedBook> res = new ArrayList<>();
        pbook1.setIdPrintedBook(11l);
        pbook1.setIdPrintedBook(12l);
        pbookdto1.setIdPrintedBook(11l);
        pbookdto2.setIdPrintedBook(12l);

        expDTO.add(pbookdto1);
        expDTO.add(pbookdto2);

        for (PrintedBookDTO pb : expDTO) {
            res.add(DTOEntityManager.printedBookDTOtoEntity(pb));
        }
        when(mockBookDao.find(book)).thenReturn(book);
        when(mockLoanDao.find(loan)).thenReturn(loan);
        when(mockpBookDao.findPrintedBooksByLoan(book, loan)).thenReturn(res);

        List<PrintedBookDTO> resDTO = pbookService.findPrintedBooksByLoan(bookdto, loandto);

        assertEquals(resDTO, expDTO);
    }

    /* @Test
     public void insertPBookTest() {
     /* Book book = new Book();
     book.setIdBook(1);
     PrintedBook pb = new PrintedBook(book, Boolean.TRUE, PrintedBook.Condition.Used, null);
     pbookService.insertPrintedBook(DTOEntityManager.printedBookEntitytoDTO(pb));
        
     List<PrintedBook> list = new ArrayList<>();
     list.add(pb);
        
     List<PrintedBookDTO> list2 = new ArrayList<>();
     list2.add(DTOEntityManager.printedBookEntitytoDTO(pb));
     when(mockpBookDao.findPrintedBooks(book)).thenReturn(list);
        
     List<PrintedBookDTO> result = pbookService.findPrintedBooksByBook(DTOEntityManager.bookEntitytoDTO(book));
        
     assertEquals(result, list2);*/
    /*
     List<PrintedBookDTO> expectedTO = new ArrayList<>();
     BookDTO bookTO2 = new BookDTO();
     bookTO2.setAuthors("author");
     bookTO2.setDepartment(BookDTO.Department.Sport);
     bookTO2.setDescription("desctiption");
     bookTO2.setISBN("1235");
     bookTO2.setName("name");
     bookTO2.setIdBook(11l);

     Book book2 = DTOEntityManager.bookDTOtoEntity(bookTO2);

     PrintedBookDTO imp1 = new PrintedBookDTO(11l, bookTO2, Boolean.TRUE, PrintedBook.Condition.Used, null);
     PrintedBookDTO imp2 = new PrintedBookDTO(22l, bookTO2, Boolean.TRUE, PrintedBook.Condition.Used, null);
     pbookService.insertPrintedBook(imp1);
     pbookService.insertPrintedBook(imp2);
     expectedTO.add(imp1);
     expectedTO.add(imp2);

     List<PrintedBook> result = new ArrayList<>();
     for (PrintedBookDTO supp : expectedTO) {
     result.add(DTOEntityManager.printedBookDTOtoEntity(supp));
     }
     when(mockpBookDao.findPrintedBooks(book2)).thenReturn(result);
     //when(mockedBookDao.findBookById(bookTO2.getIdBook())).thenReturn(book2);

     List<PrintedBookDTO> resultTO = pbookService.findPrintedBooksByBook(bookTO2);
     verify(mockpBookDao).findPrintedBooks(book2);
     for (PrintedBookDTO q : expectedTO) {
     System.out.println("ex: " + q.getIdPrintedBook());
     }

     for (PrintedBookDTO q : resultTO) {
     System.out.println("re: " + q.getIdPrintedBook());
     }
     assertEquals(expectedTO, resultTO);
     //assertEquals(Long.valueOf(1), Long.valueOf(pbookService.findPrintedBookById(1l).getIdBook()));
     */
   // }
    /*  @Test
     public void insertPrintedBookTest() {
     pbookService.insertPrintedBook(pbookdto1);
     List<PrintedBookDTO> pb = pbookService.findPrintedBooksByBook(DTOEntityManager.bookEntitytoDTO(b));
        

     //assertEquals(pb.get(0), pbookdto1);
     //assertNotNull(pbookdto1.getIdBook());
     //assertTrue(pbookdto1.getIdBook() > 0);
     //assertNotNull(pbookService.findPrintedBookById(1l));
     /*assertNull(pbookService.findPrintedBookById(1l));
     PrintedBookDTO pbook2 = pbookService.findPrintedBookById(1l);
     assertEquals(pbook2.getBook(), pbookdto1.getBook());
     assertEquals(pbook2.getCondition(), pbookdto1.getCondition());
     assertEquals(pbook2.getLoan(), pbookdto1.getLoan());
     assertEquals(pbook2.getState(), pbookdto1.getState());*/
    /*mockBookService.insertBook(book1);
     BookDTO myBook = mockBookService.findBook(book1);
     assertNotNull(myBook);
     assertNull(book1);*/
    //}*/*/
}
