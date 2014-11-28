package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.DAException;
import cz.fi.muni.pa165.dao.GenericDAO;
import cz.fi.muni.pa165.entity.Book;
import cz.fi.muni.pa165.entity.Loan;
import cz.fi.muni.pa165.entity.Member;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import org.springframework.stereotype.Component;

/**
 * Class for MemberDAOImpl
 * 
 * @author Martin Malik <374128@mail.muni.cz>
 */
@Component
public class MemberDAOImpl implements MemberDAO, GenericDAO<Member>{

    @PersistenceContext(unitName = "myUnit")
    private EntityManager entityManager;
    
    @Override 
    public List<Member> findAllMembers(){
        try{
            final Query query = entityManager.createQuery("SELECT mem FROM Member as mem");
            return query.getResultList();
        }catch(RuntimeException E) {
            throw new DAException(E.getMessage());
        }
    }
    
    @Override
    public Member findMemberByIdMember(long id) {
        if(id < 0) throw new IllegalArgumentException("id is not possitive");
        
        try {
            final Query query = entityManager.createQuery("SELECT mem FROM Member as mem WHERE mem.idMember = :id");
            query.setParameter("id", id);
            return (Member) query.getSingleResult();
        } catch(RuntimeException E) {
            throw new DAException(E.getMessage());
        }
    }

    @Override
    public List<Member> findMembersByName(String name) {
        try {
            final Query query = entityManager.createQuery("SELECT mem FROM Member as mem WHERE mem.name like :name");
            query.setParameter("name","%" + name + "%");
            return query.getResultList();
        } catch(RuntimeException E) {
            throw new DAException(E.getMessage());
        }
    }

    @Override
    public Member findMemberByEmail(String email) {
        try {
            final Query query = entityManager.createQuery("SELECT mem FROM Member AS mem WHERE mem.email = :email");
            query.setParameter("email", email);
            return (Member) query.getSingleResult();
        } catch(RuntimeException E) {
            throw new DAException(E.getMessage());
        }
    }
    
    @Override
    public List<Member> findMembersByAddress(String address) {
        try {
            final Query query = entityManager.createQuery("SELECT mem FROM Member as mem WHERE mem.address like :address");
            query.setParameter("address","%" + address + "%");
            return query.getResultList();
        } catch(RuntimeException E) {
            throw new DAException(E.getMessage());
        }
    }
    
    @Override
    public List<Member> findMembersByBook(Book book) {
        if(book == null) throw new NullPointerException("book is null");
        
        try {
            final Query query = entityManager.createQuery(
                    "SELECT pb.loan.member FROM PrintedBook AS pb WHERE pb.book.idBook = :idBook"
            );                  
            query.setParameter("idBook", book.getIdBook());
            List<Member> members = query.getResultList();
        
            return members;
        } catch(RuntimeException E) {
            throw new DAException(E.getMessage());
        }
    }
    
    public void setManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void insert(Member t) {
        System.out.println("insert v dao1: " + t);
        if(t == null) throw new NullPointerException("member is null");
        
        try {
            entityManager.persist(t);
        } catch(RuntimeException E) {
            throw new DAException(E.getMessage());
        }
        
        System.out.println("insert v dao2: " + t);
    }

    @Override
    public void update(Member t) {
        if(t == null) throw new NullPointerException("member is null");
        
        System.out.println("member update dao1 " + t);
        
        try {
            final Query query = entityManager.createQuery("UPDATE Member SET name = :name," +
                                "email = :email, address = :address WHERE idMember = :id");
            query.setParameter("id", t.getIdMember());
            query.setParameter("name", t.getName());
            query.setParameter("email", t.getEmail());
            query.setParameter("address", t.getAddress());
            query.executeUpdate();
        } catch(RuntimeException E) {
            throw new DAException(E.getMessage());
        }
        
        System.out.println("member update dao1 " + t);
        
    }

    @Override
    public void delete(Member t) {
        if(t == null) throw new NullPointerException("member is null");
        
        try {
            Member mem = entityManager.merge(t);
            entityManager.remove(mem);
        } catch(RuntimeException E) {
            throw new DAException(E.getMessage());
        }
    }

    @Override
    public Member find(Member t) {
        if(t == null) throw new NullPointerException("member is null");
        
        try {
            final Query query = entityManager.createQuery("SELECT mem FROM Member as mem WHERE mem.idMember = :id");
            query.setParameter("id", t.getIdMember());
            return (Member) query.getSingleResult();
        } catch(RuntimeException E) {
            throw new DAException(E.getMessage());
        }
    }   
}