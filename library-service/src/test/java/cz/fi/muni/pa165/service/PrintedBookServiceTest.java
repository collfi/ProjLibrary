/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.dao.BookDAOImpl;
import cz.fi.muni.pa165.dao.LoanDAOImpl;
import cz.fi.muni.pa165.dao.PrintedBookDAOImpl;
import cz.fi.muni.pa165.datatransferobject.BookDTO;
import cz.fi.muni.pa165.datatransferobject.LoanDTO;
import cz.fi.muni.pa165.datatransferobject.PrintedBookDTO;
import cz.fi.muni.pa165.entity.Book;
import cz.fi.muni.pa165.entity.Loan;
import cz.fi.muni.pa165.entity.PrintedBook;
import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.testng.Assert.assertEquals;

/**
 * @author Boris Valentovic - xvalent2
 */
public class PrintedBookServiceTest {

    private PrintedBookServiceImpl pbookService;
    @Mock
    private PrintedBookDAOImpl mockpBookDao;
    @Mock
    private BookDAOImpl mockBookDao;
    @Mock
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
        assertEquals(pbookdto1, result);
    }

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
        pbook1.setIdPrintedBook(11l);
        pbookdto1.setIdPrintedBook(11l);
        when(mockpBookDao.find(pbook1)).thenReturn(pbook1);
        PrintedBookDTO result = pbookService.findPrintedBook(pbookdto1);
        assertEquals(pbookdto1, result);

    }

    @Test
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
}
