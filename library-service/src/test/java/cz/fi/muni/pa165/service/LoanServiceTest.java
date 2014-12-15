package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.dao.BookDAOImpl;
import cz.fi.muni.pa165.dao.LoanDAOImpl;
import cz.fi.muni.pa165.dao.MemberDAOImpl;
import cz.fi.muni.pa165.datatransferobject.BookDTO;
import cz.fi.muni.pa165.datatransferobject.LoanDTO;
import cz.fi.muni.pa165.datatransferobject.MemberDTO;
import cz.fi.muni.pa165.entity.Book;
import cz.fi.muni.pa165.entity.Loan;
import cz.fi.muni.pa165.entity.Member;
import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.testng.Assert.assertEquals;

/**
 * @author Sergii Pylypenko, 430519
 */
public class LoanServiceTest {

    private LoanServiceImpl loanService;

    @Mock
    private BookDAOImpl mockBookDao;
    @Mock
    private MemberDAOImpl mockMemberDao;
    @Mock
    private LoanDAOImpl mockLoanDao;

    private Loan loan;
    private Member member;
    private Book book;
    private BookDTO bookDTO;
    private LoanDTO loanDTO;
    private MemberDTO memberDTO;
    private List<Loan> exampleLoanLst;
    private List<LoanDTO> expLoanDTOLst;

    @BeforeMethod
    public void setUp() {

        loanService = new LoanServiceImpl();
        mockMemberDao = mock(MemberDAOImpl.class);
        mockLoanDao = mock(LoanDAOImpl.class);
        mockBookDao = mock(BookDAOImpl.class);
        loanService.setBookDao(mockBookDao);
        loanService.setLoanDao(mockLoanDao);
        loanService.setMemberDao(mockMemberDao);

        loan = new Loan();
        loan.setIdLoan(20);
        loanDTO = DTOEntityManager.loanEntitytoDTO(loan);

        member = new Member();
        member.setIdMember(21);
        memberDTO = DTOEntityManager.memberEntitytoDTO(member);

        book = new Book();
        book.setIdBook(22);
        bookDTO = DTOEntityManager.bookEntitytoDTO(book);


        expLoanDTOLst = new ArrayList<>();
        expLoanDTOLst.add(loanDTO);

        exampleLoanLst = new ArrayList<>();
        exampleLoanLst.add(loan);
    }

    @Test
    public void findLoanByIdTest() {
        int id = 11;
        loan.setIdLoan(id);
        loanDTO.setIdLoan(id);
        when(mockLoanDao.findLoanById(id)).thenReturn(loan);
        LoanDTO result = loanService.findLoanById(id);
        assertEquals(loanDTO, result);
    }

    @Test
    public void insertTest() {
        doThrow(new RuntimeException()).when(mockLoanDao).insert(loan);
        try {
            loanService.insertLoan(loanDTO);
        } catch (RuntimeException re) {
        }
    }

    @Test
    public void updateTest() {
        doThrow(new RuntimeException()).when(mockLoanDao).update(loan);
        try {
            loanService.updateLoan(loanDTO);
        } catch (RuntimeException re) {
        }
    }

    @Test
    public void deleteTest() {
        doThrow(new RuntimeException()).when(mockLoanDao).delete(loan);
        try {
            loanService.deleteLoan(loanDTO);
        } catch (RuntimeException re) {
        }
    }

    @Test
    public void findLoanTest() {
        when(mockLoanDao.find(loan)).thenReturn(loan);
        System.out.println(loanDTO);
        LoanDTO result = loanService.findLoan(loanDTO);
        assertEquals(loanDTO, result);

    }

    @Test
    public void findLoansByMemberTest() {
        when(mockLoanDao.findAllLoansByMember(member, false)).thenReturn(exampleLoanLst);
        when(mockMemberDao.find(member)).thenReturn(member);

        List<LoanDTO> resDTO = loanService.findAllLoansByMember(memberDTO, false);
        assertEquals(resDTO, expLoanDTOLst);
    }

    @Test
    public void findAllLoandsFromToTest() {
        Date d = new Date();
        Date d2 = new Date();
        when(mockLoanDao.findAllLoandsFromTo(d, d2)).thenReturn(exampleLoanLst);
        List<LoanDTO> resDTO = loanService.findAllLoandsFromTo(d, d2);
        assertEquals(resDTO, expLoanDTOLst);
    }

    @Test
    public void findAllLoansWithBookTest() {
        when(mockBookDao.find(book)).thenReturn(book);
        when(mockLoanDao.findAllLoansWithBook(book)).thenReturn(exampleLoanLst);
        List<LoanDTO> resDTO = loanService.findAllLoansWithBook(bookDTO);
        assertEquals(resDTO, expLoanDTOLst);
    }

}
