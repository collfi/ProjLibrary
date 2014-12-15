package cz.fi.muni.pa165;

import cz.fi.muni.pa165.dao.MemberDAOImpl;
import cz.fi.muni.pa165.entity.Book;
import cz.fi.muni.pa165.entity.Loan;
import cz.fi.muni.pa165.entity.Member;
import cz.fi.muni.pa165.entity.PrintedBook;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import java.util.Date;
import java.util.List;

import static org.testng.Assert.assertEquals;

/**
 * Test for method of class MemberDAOImpl
 *
 * @author Pylypenko Sergii <430519@mail.muni.cz>
 */
@ContextConfiguration(classes = DaoContext.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class MemberDAOTest extends AbstractTestNGSpringContextTests {

    @PersistenceUnit
    public EntityManagerFactory emf;
    @PersistenceContext
    public EntityManager em;

    @DirtiesContext
    @BeforeMethod
    public void setUp() {
        em = emf.createEntityManager();
        em.getTransaction().begin();

        Member member1 = new Member();
        member1.setName("John Black");
        member1.setEmail("john.black@muni.mail.cz");
        member1.setAddress("Tererova 164/56,Brno");

        em.persist(member1);
        em.getTransaction().commit();
        em.close();
    }

    @Test
    public void findMemberByEmail() {
        EntityManager em = emf.createEntityManager();
        MemberDAOImpl memDAO = new MemberDAOImpl();
        memDAO.setManager(em);
        Member member = memDAO.findMemberByEmail("john.black@muni.mail.cz");
        em.close();

        assertEquals("john.black@muni.mail.cz", member.getEmail());
    }

    @Test
    public void insertMember() {
        EntityManager em = emf.createEntityManager();
        MemberDAOImpl memDAO = new MemberDAOImpl();
        memDAO.setManager(em);

        Member member = new Member();
        member.setName("Lucy Red");
        member.setAddress("1856/12, Cerna Pole, Brno");
        member.setEmail("lucy.red@mail.muni.cz");

        em.getTransaction().begin();
        memDAO.insert(member);
        em.getTransaction().commit();

        List<Member> members = em.createQuery("SELECT mem FROM Member mem", Member.class).getResultList();
        em.close();

        assertEquals(2, members.size());
    }

    @Test
    public void findMember() {
        EntityManager em = emf.createEntityManager();
        MemberDAOImpl memDAO = new MemberDAOImpl();
        memDAO.setManager(em);
        Member member = new Member();
        member.setIdMember(1);
        Member member2 = memDAO.find(member);
        em.close();

        assertEquals(1, member2.getIdMember());
    }

    @Test
    public void deleteMember() {
        EntityManager em = emf.createEntityManager();
        MemberDAOImpl memDAO = new MemberDAOImpl();
        memDAO.setManager(em);

        em.getTransaction().begin();
        Member member = memDAO.findMemberByIdMember(1);
        memDAO.delete(member);
        em.getTransaction().commit();

        List<Member> members = em.createQuery("SELECT mem FROM Member mem", Member.class).getResultList();
        em.close();

        assertEquals(0, members.size());
    }

    @Test
    public void updateMember() {
        EntityManager em = emf.createEntityManager();
        MemberDAOImpl memDAO = new MemberDAOImpl();
        memDAO.setManager(em);

        Member member = new Member();
        member.setName("Lucy Red");
        member.setAddress("1856/12, Cerna Pole, Brno");
        member.setEmail("lucy.red@mail.muni.cz");

        em.getTransaction().begin();
        em.persist(member);
        em.getTransaction().commit();

        member.setName("Lucy Brown");
        member.setEmail("l.red@gmail.com");
        member.setAddress("142/12, Kralovo Pole, Brno");

        em.getTransaction().begin();
        memDAO.update(member);
        em.getTransaction().commit();


        String name = memDAO.find(member).getName();
        String email = memDAO.find(member).getEmail();
        String address = memDAO.find(member).getAddress();

        em.close();

        assertEquals("Lucy Brown", member.getName());
        assertEquals("l.red@gmail.com", member.getEmail());
        assertEquals("142/12, Kralovo Pole, Brno", member.getAddress());

        assertEquals("Lucy Brown", name);
        assertEquals("l.red@gmail.com", email);
        assertEquals("142/12, Kralovo Pole, Brno", address);
    }

    @Test
    public void findMembersByName() {
        EntityManager em = emf.createEntityManager();
        MemberDAOImpl memDAO = new MemberDAOImpl();
        memDAO.setManager(em);

        Member member2 = new Member();
        member2.setName("John White");
        member2.setEmail("john.white@mail.muni.cz");
        member2.setAddress("Hrncirska 123/8, Kralovo Pole, Brno");

        Member member3 = new Member();
        member3.setName("John Green");
        member3.setEmail("john.green@mail.muni.cz");
        member3.setAddress("123/56, Cerna Pole, Brno");

        em.getTransaction().begin();
        em.persist(member2);
        em.persist(member3);
        em.getTransaction().commit();

        List<Member> members = memDAO.findMembersByName("John");

        em.close();

        //John Black from method setUp and 2 others
        assertEquals(3, members.size());
    }

    @Test
    public void findMembersByAddress() {
        EntityManager em = emf.createEntityManager();
        MemberDAOImpl memDAO = new MemberDAOImpl();
        memDAO.setManager(em);

        Member member1 = new Member();
        member1.setName("John White");
        member1.setEmail("john.white@mail.muni.cz");
        member1.setAddress("Hrncirska 123/8, Kralovo Pole, Brno");

        em.getTransaction().begin();
        em.persist(member1);
        em.getTransaction().commit();

        List<Member> members = memDAO.findMembersByAddress("Brno");

        em.close();

        //John Black Brno from method setUp
        assertEquals(members.size(), 2);
    }

    @Test
    public void findMembersByBook() {
        EntityManager em = emf.createEntityManager();
        MemberDAOImpl memDAO = new MemberDAOImpl();
        memDAO.setManager(em);

        Member member1 = new Member();
        member1.setName("John White");
        member1.setEmail("john.white@mail.muni.cz");
        member1.setAddress("Hrncirska 123/8, Kralovo Pole, Brno");

        Member member2 = new Member();
        member2.setName("John Green");
        member2.setEmail("john.green@mail.muni.cz");
        member2.setAddress("123/56, Cerna Pole, Brno");

        Book book = new Book();
        book.setName("Harry Potter");
        book.setISBN("123112315");
        book.setDescription("Book about Wizard!");
        book.setAuthors("J.K. Rowling");
        book.setDepartment(Book.Department.Sport);

        PrintedBook pb1 = new PrintedBook();
        pb1.setBook(book);
        pb1.setCondition(PrintedBook.Condition.Used);

        PrintedBook pb2 = new PrintedBook();
        pb2.setBook(book);
        pb2.setCondition(PrintedBook.Condition.Used);

        Loan loan1 = new Loan();
        loan1.setReturned(false);
        loan1.setToDate(new Date());
        loan1.setFromDate(new Date());
        loan1.setDateReturned(new Date());
        loan1.setMember(member1);

        Loan loan2 = new Loan();
        loan2.setReturned(false);
        loan2.setToDate(new Date());
        loan2.setFromDate(new Date());
        loan2.setDateReturned(new Date());
        loan2.setMember(member2);

        pb1.setLoan(loan1);

        pb2.setLoan(loan2);


        em.getTransaction().begin();
        em.persist(member1);
        em.persist(member2);
        em.persist(book);
        em.persist(pb1);
        em.persist(pb2);
        em.persist(loan1);
        em.persist(loan2);
        em.getTransaction().commit();

        List<Member> mems = memDAO.findMembersByBook(book);

        em.close();

        assertEquals(2, mems.size());
    }
}