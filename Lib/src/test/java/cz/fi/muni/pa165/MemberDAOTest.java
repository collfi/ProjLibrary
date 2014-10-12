/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.fi.muni.pa165;

import cz.fi.muni.pa165.DaoContext;
import cz.fi.muni.pa165.dao.MemberDAO;
import cz.fi.muni.pa165.entity.Member;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author
 */
@ContextConfiguration(classes = DaoContext.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class MemberDAOTest extends AbstractTestNGSpringContextTests{
    
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
    public void findMemberByEmail(){
        EntityManager em = emf.createEntityManager();
        MemberDAO memDAO = new MemberDAO();
        memDAO.setManager(em);
        Member member = memDAO.findMemberByEmail("john.black@muni.mail.cz");
        em.close();
        assertEquals(member.getEmail(), "john.black@muni.mail.cz");
    }
    
    @Test
    public void findMember(){
        EntityManager em = emf.createEntityManager();
        MemberDAO memDAO = new MemberDAO();
        memDAO.setManager(em);
        Member member = new Member();
        member.setIdMember(1);
        Member member2 = memDAO.find(member);
        em.close();
        assertEquals(1, member2.getIdMember());
    }
    
    @Test
    public void updateMember(){
        EntityManager em = emf.createEntityManager();
        MemberDAO memDAO = new MemberDAO();
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
        
        memDAO.update(member);
        
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
    public void findMemberByName(){
        EntityManager em = emf.createEntityManager();
        MemberDAO memDAO = new MemberDAO();
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
        
        List<Member> members = memDAO.findMemberByName("John");
        
        em.close();
        
        //John Black from method setUp and 2 others
        assertEquals(3, members.size());
    }
}