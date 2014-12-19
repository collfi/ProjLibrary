/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.dto;

import cz.fi.muni.pa165.dao.BookDAOImpl;
import cz.fi.muni.pa165.dao.DaoContext;
import cz.fi.muni.pa165.entity.Book;
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
import java.util.List;

import static org.testng.Assert.assertEquals;

/**
 * @author Boris Valentovic - xvalent2
 */
@ContextConfiguration(classes = DaoContext.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class BookDAOTest extends AbstractTestNGSpringContextTests {

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
        em.close();

    }

    @Test
    public void testFindBooksByName() {
        EntityManager em = emf.createEntityManager();
        BookDAOImpl bdao = new BookDAOImpl();
        bdao.setManager(em);
        List<Book> books = bdao.findBooksByName("Harry");

        assertEquals(1, books.size());
    }

    @Test
    public void testInsert() {

        EntityManager em = emf.createEntityManager();
        BookDAOImpl bdao = new BookDAOImpl();
        bdao.setManager(em);

        Book book = new Book();
        book.setAuthors("Author");
        book.setDepartment(Department.Sport);
        book.setDescription("Description");
        book.setISBN("123456456");
        book.setName("Name");

        em.getTransaction().begin();

        //book.setPrintedBooks(null);
        bdao.insert(book);
        em.getTransaction().commit();

        // em.getTransaction().begin();
        //final Query query = em.createQuery("SELECT idBook FROM Book");
        List<Book> books = em.createQuery("SELECT b FROM Book b", Book.class).getResultList();
        //em.getTransaction().commit();
        em.close();
        assertEquals(books.size(), 2);
    }

    @Test
    public void testDelete() {
        EntityManager em = emf.createEntityManager();
        BookDAOImpl bdao = new BookDAOImpl();
        bdao.setManager(em);
        Book b = new Book();
        b.setIdBook(1);
        em.getTransaction().begin();
        b = bdao.find(b);
        bdao.delete(b);
        em.getTransaction().commit();

        List<Book> books = em.createQuery("SELECT b FROM Book b", Book.class).getResultList();
        em.close();
        assertEquals(books.size(), 0);
    }

    @Test
    public void testUpdate() {
        EntityManager em = emf.createEntityManager();
        BookDAOImpl bdao = new BookDAOImpl();
        bdao.setManager(em);
        Book book = new Book();
        book = em.createQuery("SELECT b FROM Book b", Book.class).getSingleResult();
        em.getTransaction().begin();
        book.setName("Updated!");
        bdao.update(book);
        em.getTransaction().commit();

        Book book2 = em.createQuery("SELECT b FROM Book b", Book.class).getSingleResult();
        em.close();
        assertEquals(book2.getName(), "Updated!");

    }

    @Test
    public void testFindBooksByISBN() {
        EntityManager em = emf.createEntityManager();
        BookDAOImpl bdao = new BookDAOImpl();
        bdao.setManager(em);
        List<Book> books = bdao.findBooksByISBN("123112315");

        assertEquals(1, books.size());
    }

    @Test
    public void testFindBooksByAuthor() {
        EntityManager em = emf.createEntityManager();
        BookDAOImpl bdao = new BookDAOImpl();
        bdao.setManager(em);
        List<Book> books = bdao.findBooksByAuthor("J.K. Rowling");

        assertEquals(1, books.size());
    }

    @Test
    public void testFindBooksByDepertment() {
        EntityManager em = emf.createEntityManager();
        BookDAOImpl bdao = new BookDAOImpl();
        bdao.setManager(em);
        List<Book> books = bdao.findBooksByDepartment(Department.Sport);


        assertEquals(1, books.size());
    }

    @Test
    public void testFind() {
        EntityManager em = emf.createEntityManager();
        BookDAOImpl bdao = new BookDAOImpl();
        Book b = new Book();
        b.setIdBook(1);
        bdao.setManager(em);
        Book books = bdao.find(b);

        assertEquals(b, books);
    }
}
