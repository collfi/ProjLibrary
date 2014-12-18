package cz.fi.muni.pa165.dto;

import cz.fi.muni.pa165.dao.DaoContext;
import cz.fi.muni.pa165.dao.BookDAOImpl;
import cz.fi.muni.pa165.dao.LoanDAOImpl;
import cz.fi.muni.pa165.dao.MemberDAOImpl;
import cz.fi.muni.pa165.entity.Book;
import cz.fi.muni.pa165.entity.Loan;
import cz.fi.muni.pa165.entity.Member;
import cz.fi.muni.pa165.entity.PrintedBook;
import cz.fi.muni.pa165.library.api.constants.Condition;
import cz.fi.muni.pa165.library.api.constants.Department;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

/**
 * @author Martin Malik<374128@mail.muni.cz>
 */
@ContextConfiguration(classes = DaoContext.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class LoanDAOTest extends AbstractTestNGSpringContextTests {

    @PersistenceUnit
    public EntityManagerFactory emf;
    @PersistenceContext
    public EntityManager em;

    SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");


    @DirtiesContext
    @BeforeMethod
    public void setup() {
        em = emf.createEntityManager();
        em.getTransaction().begin();
        Book book = new Book();
        book.setName("Harry Potter");
        book.setISBN("123112315");
        book.setDescription("Book about Wizard!");
        book.setAuthors("J.K. Rowling");
        book.setDepartment(Department.Sport);

        Member m = new Member();
        m.setAddress("Vinarska");
        m.setEmail("cruel.coder@gmail.com");
        m.setName("Cruel Coder");

        PrintedBook pb1 = new PrintedBook();
        pb1.setBook(book);
        pb1.setCondition(Condition.Used);
        pb1.setState(Boolean.FALSE);

        PrintedBook pb2 = new PrintedBook();
        pb2.setBook(book);
        pb2.setCondition(Condition.Used);
        pb2.setState(Boolean.TRUE);

        Loan l = new Loan();
        l.setReturned(false);
        l.setToDate(new Date());
        l.setFromDate(new Date());
        l.setPrintedBook(pb1);
        l.setDateReturned(new Date());
        l.setMember(m);
        pb2.setLoan(l);

        em.persist(book);
        em.persist(l);
        em.persist(m);
        em.persist(pb1);
        em.persist(pb2);
        em.getTransaction().commit();
        em.close();
    }

    @Test
    public void testFindAllLoandsFromTo() throws ParseException {
        EntityManager em = emf.createEntityManager();
        LoanDAOImpl ldao = new LoanDAOImpl();
        ldao.setManager(em);
        List<Loan> loans = ldao.findAllLoandsFromTo(ft.parse("1818-11-11"), ft.parse("2018-11-11"));
        assertEquals(1, loans.size());
    }

    @Test
    public void testLoanContainsBooks() {
        EntityManager em = emf.createEntityManager();
        LoanDAOImpl ldao = new LoanDAOImpl();
        ldao.setManager(em);
        Loan l = new Loan();
        l.setReturned(false);
        l = ldao.find(l);
        assertEquals(l.getPrintedBook().getBook().getName(), "Harry Potter");
    }

    @Test
    public void testFindAllLoansByMember() {
        EntityManager em = emf.createEntityManager();
        LoanDAOImpl ldao = new LoanDAOImpl();
        ldao.setManager(em);
        MemberDAOImpl mbd = new MemberDAOImpl();
        mbd.setManager(em);
        Member member = mbd.findMemberByEmail("cruel.coder@gmail.com");
        List<Loan> loans = ldao.findAllLoansByMember(member, false);
        assertEquals(1, loans.size());
    }

    @Test
    public void testFindAllLoansWithBook() {
        EntityManager em = emf.createEntityManager();
        LoanDAOImpl ldao = new LoanDAOImpl();
        ldao.setManager(em);

        BookDAOImpl bdao = new BookDAOImpl();
        bdao.setManager(em);
        List<Book> books = bdao.findBooksByISBN("123112315");

        List<Loan> loans = ldao.findAllLoansWithBook(books.get(0));
        assertEquals(1, loans.size());
    }

    @Test
    public void testFind() {
        EntityManager em = emf.createEntityManager();
        LoanDAOImpl ldao = new LoanDAOImpl();
        ldao.setManager(em);
        Loan l = new Loan();
        l.setIdLoan(1);
        Loan found = ldao.find(l);
        assertEquals(1, found.getIdLoan());
    }

    @Test
    public void testDelete() {

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        LoanDAOImpl ldao = new LoanDAOImpl();
        ldao.setManager(em);
        Loan l = new Loan();
        l.setIdLoan(1);
        Loan found = ldao.find(l);
        assertEquals(1, found.getIdLoan());

        ldao.delete(found);
        em.getTransaction().commit();

        try {
            ldao.find(l);
            fail("Method didn't throw when it had to");
        } catch (Exception e) {
        }

    }
}