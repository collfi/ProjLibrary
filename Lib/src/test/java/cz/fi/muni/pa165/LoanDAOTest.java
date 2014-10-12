package cz.fi.muni.pa165;

import cz.fi.muni.pa165.dao.BookDAO;
import cz.fi.muni.pa165.dao.LoanDAO;
import cz.fi.muni.pa165.dao.PrintedBookDAO;
import cz.fi.muni.pa165.entity.Book;
import cz.fi.muni.pa165.entity.Loan;
import cz.fi.muni.pa165.entity.Member;
import cz.fi.muni.pa165.entity.PrintedBook;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

/**
 * Created by sergii on 12.10.14.
 */
@ContextConfiguration(classes = DaoContext.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class LoanDAOTest extends AbstractTestNGSpringContextTests {

    @PersistenceUnit
    public EntityManagerFactory emf;
    @PersistenceContext
    public EntityManager em;

    SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");


    @DirtiesContext
    @BeforeMethod
    public void setup() {
        em  = emf.createEntityManager();
        em.getTransaction().begin();
        ///???toto tu ma byt?
        //TODO +1 maybe put it somewhere to call it from other tests. this code is duplicated n-times
        Book book = new Book();
        book.setName("Harry Potter");
        book.setISBN("123112315");
        book.setDescription("Book about Wizard!");
        book.setAuthors("J.K. Rowling");
        book.setDapertment(Book.Department.Sport);

        Member m = new Member();
        m.setAddress("Vinarska");
        m.setEmail("cruel.coder@gmail.com");
        m.setName("Cruel Coder");

        PrintedBook pb1 = new PrintedBook();
        pb1.setBook(book);
        pb1.setCondition(PrintedBook.Condition.Used);
        pb1.setState(Boolean.FALSE);

        PrintedBook pb2 = new PrintedBook();
        pb2.setBook(book);
        pb2.setCondition(PrintedBook.Condition.Used);
        pb2.setState(Boolean.TRUE);

        Loan l = new Loan();
        l.setReturned(false);
        l.setToDate(new Date());
        l.setFromDate(new Date());
        l.setWhen(new Date());
        l.setMember(m);
//        HashSet<PrintedBook> set = new HashSet<PrintedBook>();
//        set.add(pb2);
//        l.setPbooks(set);
        pb2.setLoan(l);

        em.persist(book);
        em.persist(l);
        em.persist(m);
        em.persist(pb1);
        em.persist(pb2);
        em.getTransaction().commit();
        em.close();

    }
//
//    @DirtiesContext
//    @BeforeMethod
//    public void tearDown() {
//        em.getTransaction().rollback();
//    }


    @Test
    public void testFindAllLoandsFromTo() throws ParseException{
        EntityManager em = emf.createEntityManager();
        LoanDAO ldao = new LoanDAO();
        ldao.setManager(em);
        System.out.println(ft.parse("2018-11-11"));
        List<Loan> loans = ldao.FindAllLoandsFromTo(ft.parse("1818-11-11"), ft.parse("2018-11-11"));
        assertEquals(1, loans.size());
    }

    @Test
    public void testFindAllLoansByMember() {
        EntityManager em = emf.createEntityManager();
        LoanDAO ldao = new LoanDAO();
        ldao.setManager(em);
        //TODO I need member DAO to get member
//        List<Loan> loans = ldao.FindAllLoansByMember();
//        assertEquals(1, loans.size());
    }

    @Test
    public void testFindAllLoansWithBook() {
        EntityManager em = emf.createEntityManager();
        LoanDAO ldao = new LoanDAO();
        ldao.setManager(em);

        //TODO not so unit..
        BookDAO bdao = new BookDAO();
        bdao.setManager(em);
        List<Book> books = bdao.findBooksByISBN("123112315");

        List<Loan> loans = ldao.FindAllLoansWithBook(books.get(0));
        assertEquals(1, loans.size());
    }

    @Test
    public void testFind() {
        EntityManager em = emf.createEntityManager();
        LoanDAO ldao = new LoanDAO();
        ldao.setManager(em);
        Loan l = new Loan();
        l.setIdLoan(1);
        Loan found = ldao.find(l);
        assertEquals(1, found.getIdLoan());
    }

//    @Test
//    public void testDelete() {
//
//        EntityManager em = emf.createEntityManager();
//        LoanDAO ldao = new LoanDAO();
//        ldao.setManager(em);
//        Loan l = new Loan();
//        l.setIdLoan(1);
//        Loan found = ldao.find(l);
//        assertEquals(1, found.getIdLoan());
//
//        ldao.delete(found);
//
//        try {
//            Loan loan2 = ldao.find(l);
//            fail( "My method didn't throw when I expected it to" );
//        }
//        catch (NoResultException e){
//
//        }
//
//
//    }
}