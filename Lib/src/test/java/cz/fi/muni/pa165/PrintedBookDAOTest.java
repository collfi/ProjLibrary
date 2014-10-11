/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165;
import cz.fi.muni.pa165.DaoContext;
import cz.fi.muni.pa165.dao.BookDAO;
import cz.fi.muni.pa165.dao.PrintedBookDAO;
import cz.fi.muni.pa165.entity.Book;
import cz.fi.muni.pa165.entity.PrintedBook;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.spi.LoadState;

import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.jpa.internal.util.PersistenceUtilHelper;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Boris Valentovic - xvalent2
 */
@ContextConfiguration(classes = DaoContext.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class PrintedBookDAOTest  extends AbstractTestNGSpringContextTests {
    
    @PersistenceUnit
    public EntityManagerFactory emf;
    @PersistenceContext
    public EntityManager em;

    @DirtiesContext
    @BeforeMethod
    public void setup() {

        em = emf.createEntityManager();
        em.getTransaction().begin();
	///???toto tu ma byt?	
        Book book = new Book();
	book.setName("Harry Potter");		
	book.setISBN("123112315");
	book.setDescription("Book about Wizard!");
        book.setAuthors("J.K. Rowling");
        book.setDapertment(Book.Department.Sport);
        
        em.persist(book);
	em.getTransaction().commit();
	//em.close();
        
        
        em.getTransaction().begin();
        
        
        PrintedBook pb = new PrintedBook();
        pb.setBook(book);
        pb.setCondition(PrintedBook.Condition.Used);
        pb.setState(Boolean.FALSE);
        em.persist(pb);
        em.getTransaction().commit();
        em.close();

    }

    @Test
    public void test() {
        System.out.println("TEST");
        //zmenit
        
        EntityManager em = emf.createEntityManager();
        PrintedBookDAO pbDAO = new PrintedBookDAO();
        pbDAO.setManager(em);
        PrintedBook pb = pbDAO.findPrintedBookById(1);

        System.out.println("pb: " + pb.toString());


    }
    
}
