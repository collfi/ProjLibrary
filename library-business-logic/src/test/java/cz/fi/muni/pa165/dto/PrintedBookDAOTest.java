/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.dto;

import cz.fi.muni.pa165.dao.DaoContext;
import cz.fi.muni.pa165.dao.PrintedBookDAOImpl;
import cz.fi.muni.pa165.entity.Book;
import cz.fi.muni.pa165.entity.Loan;
import cz.fi.muni.pa165.entity.PrintedBook;
import cz.fi.muni.pa165.library.api.constants.Condition;
import cz.fi.muni.pa165.library.api.constants.Department;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import static org.testng.Assert.assertEquals;

/**
 * @author michal.lukac, xlukac, 430614
 */
@ContextConfiguration(classes = DaoContext.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class PrintedBookDAOTest extends AbstractTestNGSpringContextTests {

    @PersistenceUnit
    public EntityManagerFactory emf;
    @PersistenceContext
    public EntityManager em;

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

        em.persist(book);
        em.getTransaction().commit();

        em.getTransaction().begin();

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
        l.setDateReturned(new Date());
        pb2.setLoan(l);

        em.persist(l);
        em.persist(pb1);
        em.persist(pb2);
        em.getTransaction().commit();
        em.close();
    }

    @Test
    public void testFindprintedBookById() {
        EntityManager em = emf.createEntityManager();
        PrintedBookDAOImpl pbDAO = new PrintedBookDAOImpl();
        pbDAO.setManager(em);
        PrintedBook pb = pbDAO.findPrintedBookById(1);

        em.close();
        assertEquals(1, pb.getIdPrintedBook());
    }

    @Test
    public void testFindPrintedBooks() {
        EntityManager em = emf.createEntityManager();
        PrintedBookDAOImpl pbDAO = new PrintedBookDAOImpl();
        pbDAO.setManager(em);
        Book b = new Book();
        b.setIdBook(1);

        List<PrintedBook> l = pbDAO.findPrintedBooks(b);
        em.close();
        assertEquals(2, l.size());
    }

    @Test
    public void testFindPrintedBooksByState() {
        EntityManager em = emf.createEntityManager();
        PrintedBookDAOImpl pbDAO = new PrintedBookDAOImpl();
        pbDAO.setManager(em);
        Book b = new Book();
        b.setIdBook(1);

        List<PrintedBook> l = pbDAO.findPrintedBooksByState(b, Boolean.FALSE);
        em.close();
        assertEquals(1, l.size());
    }

    @Test
    public void testFindPrintedBooksByLoan() {
        EntityManager em = emf.createEntityManager();
        PrintedBookDAOImpl pbDAO = new PrintedBookDAOImpl();
        pbDAO.setManager(em);
        Book b = new Book();
        b.setIdBook(1);

        Loan l = new Loan();
        l.setIdLoan(1);

        List<PrintedBook> li = pbDAO.findPrintedBooksByLoan(b, l);
        em.close();
        assertEquals(1, li.size());
    }

    @Test
    public void testInsert() {
        EntityManager em = emf.createEntityManager();
        PrintedBookDAOImpl bdao = new PrintedBookDAOImpl();
        bdao.setManager(em);

        em.getTransaction().begin();

        Book book = new Book();
        book.setName("Harry Potter");
        book.setISBN("123112315");
        book.setDescription("Book about Wizard!");
        book.setAuthors("J.K. Rowling");
        book.setDepartment(Department.Sport);
        book.setIdBook(1);

        PrintedBook pbook = new PrintedBook();
        pbook.setBook(book);
        pbook.setState(Boolean.FALSE);
        pbook.setCondition(Condition.Damaged);
        bdao.insert(pbook);

        em.getTransaction().commit();

        List<PrintedBook> books = em.createQuery("SELECT b FROM PrintedBook b", PrintedBook.class).getResultList();

        em.close();
        assertEquals(books.size(), 3);
    }

    @Test
    public void testDelete() {
        EntityManager em = emf.createEntityManager();
        PrintedBookDAOImpl bdao = new PrintedBookDAOImpl();
        bdao.setManager(em);
        em.getTransaction().begin();

        Book book = new Book();
        book.setName("Harry Potter");
        book.setISBN("123112315");
        book.setDescription("Book about Wizard!");
        book.setAuthors("J.K. Rowling");
        book.setDepartment(Department.Sport);
        book.setIdBook(1);

        PrintedBook pbook = new PrintedBook();
        pbook.setBook(book);
        pbook.setState(Boolean.FALSE);
        pbook.setCondition(Condition.New);
        pbook.setIdPrintedBook(1);

        bdao.delete(pbook);
        em.getTransaction().commit();
        List<PrintedBook> books = em.createQuery("SELECT b FROM PrintedBook b", PrintedBook.class).getResultList();

        em.close();
        assertEquals(books.size(), 1);
    }

    @Test
    public void testUpdate() {
        EntityManager em = emf.createEntityManager();
        PrintedBookDAOImpl bdao = new PrintedBookDAOImpl();
        bdao.setManager(em);
        em.getTransaction().begin();

        Book book = new Book();
        book.setName("Harry Potter");
        book.setISBN("123112315");
        book.setDescription("Book about Wizard!");
        book.setAuthors("J.K. Rowling");
        book.setDepartment(Department.Sport);
        book.setIdBook(1);

        PrintedBook pbook = new PrintedBook();
        pbook.setBook(book);
        pbook.setState(Boolean.FALSE);
        pbook.setCondition(Condition.New);
        pbook.setIdPrintedBook(2);
        bdao.update(pbook);

        PrintedBook b1 = bdao.find(pbook);
        em.getTransaction().commit();
        em.close();
        assertEquals(b1.getState(), Boolean.FALSE);
    }

    @Test
    public void testFind() {
        EntityManager em = emf.createEntityManager();
        PrintedBookDAOImpl pbDAO = new PrintedBookDAOImpl();
        pbDAO.setManager(em);
        PrintedBook book = new PrintedBook();
        book.setIdPrintedBook(1);
        PrintedBook pb = pbDAO.find(book);
        em.close();
        assertEquals(1, pb.getIdPrintedBook());
    }

    @Test
    public void testFindAllBorrowedPrintedBooks() {
        EntityManager em = emf.createEntityManager();
        PrintedBookDAOImpl pbDAO = new PrintedBookDAOImpl();
        pbDAO.setManager(em);
        PrintedBook book = new PrintedBook();
        book.setIdPrintedBook(1);
        PrintedBook pb = pbDAO.find(book);
        em.close();
        assertEquals(1, pb.getIdPrintedBook());
    }

    @Test
    public void testFindAllPrintedBooksByLoan() {
        EntityManager em = emf.createEntityManager();
        PrintedBookDAOImpl pbDAO = new PrintedBookDAOImpl();
        pbDAO.setManager(em);

        Book b = new Book();
        b.setIdBook(1);

        PrintedBook book = pbDAO.findPrintedBookById(1);
        book.setIdPrintedBook(1);
        HashSet<PrintedBook> set = new HashSet<PrintedBook>();

        Loan l = new Loan();
        l.setIdLoan(1);
        l.setPrintedBook(book);
        book.setLoan(l);
        book.setState(Boolean.TRUE);
        pbDAO.update(book);

        List<PrintedBook> li = pbDAO.findPrintedBooksByLoan(b, l);
        em.close();
        assertEquals(1, li.size());
    }
}
