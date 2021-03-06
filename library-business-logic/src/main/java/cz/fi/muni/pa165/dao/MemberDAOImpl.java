package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.entity.Book;
import cz.fi.muni.pa165.entity.Member;
import cz.fi.muni.pa165.library.api.exceptions.DuplicationException;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import javax.persistence.PersistenceException;

/**
 * Class for MemberDAOImpl
 *
 * @author Martin Malik <374128@mail.muni.cz>
 */
@Component
public class MemberDAOImpl implements MemberDAO {

    @PersistenceContext(unitName = "myUnit")
    private EntityManager entityManager;

    @Override
    public List<Member> findAllMembers() {
        try {
            final Query query = entityManager.createQuery("SELECT mem FROM Member as mem");
            return query.getResultList();
        } catch (RuntimeException E) {
            throw new DAException(E.getMessage());
        }
    }

    @Override
    public Member findMemberByIdMember(long id) {
        if (id < 0) throw new IllegalArgumentException("id is not possitive");

        try {
            final Query query = entityManager.createQuery("SELECT mem FROM Member as mem WHERE mem.idMember = :id");
            query.setParameter("id", id);
            return (Member) query.getSingleResult();
        } catch (RuntimeException E) {
            throw new DAException(E.getMessage());
        }
    }

    @Override
    public List<Member> findMembersByName(String name) {
        try {
            final Query query = entityManager.createQuery("SELECT mem FROM Member as mem WHERE mem.name like :name");
            query.setParameter("name", "%" + name + "%");
            return query.getResultList();
        } catch (RuntimeException E) {
            throw new DAException(E.getMessage());
        }
    }

    @Override
    public List<Member> findMembersByEmail(String email) {
        try {
            final TypedQuery<Member> query = entityManager.createQuery("SELECT mem FROM Member AS mem " +
                    "WHERE mem.email like :email", Member.class);
            query.setParameter("email", "%" + email + "%");
            return query.getResultList();
        } catch (RuntimeException E) {
            throw new DAException(E.getMessage());
        }
    }

    @Override
    public List<Member> findMembersByAddress(String address) {
        try {
            final Query query = entityManager.createQuery("SELECT mem FROM Member as mem WHERE mem.address like :address");
            query.setParameter("address", "%" + address + "%");
            return query.getResultList();
        } catch (RuntimeException E) {
            throw new DAException(E.getMessage());
        }
    }

    @Override
    public List<Member> findMembersByBook(Book book) {
        if (book == null) throw new NullPointerException("book is null");

        try {
            final Query query = entityManager.createQuery(
                    "SELECT pb.loan.member FROM PrintedBook AS pb WHERE pb.book.idBook = :idBook"
            );
            query.setParameter("idBook", book.getIdBook());
            List<Member> members = query.getResultList();

            return members;
        } catch (RuntimeException E) {
            throw new DAException(E.getMessage());
        }
    }

    public void setManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void insert(Member t) {
        if (t == null) throw new NullPointerException("member is null");

        try {
            entityManager.persist(t);
        } catch (PersistenceException pe) {
            throw new DuplicationException(pe.getMessage());
        } catch (RuntimeException E) {
            throw new DAException(E.getMessage());
        }

    }

    @Override
    public void update(Member t) {
        if (t == null) throw new NullPointerException("member is null");

        try {
            final Query query = entityManager.createQuery("UPDATE Member SET name = :name," +
                    "email = :email, address = :address WHERE idMember = :id");
            query.setParameter("id", t.getIdMember());
            query.setParameter("name", t.getName());
            query.setParameter("email", t.getEmail());
            query.setParameter("address", t.getAddress());
            query.executeUpdate();
        } catch (PersistenceException pe) {
            throw new DuplicationException(pe.getMessage());
        } catch (RuntimeException E) {
            throw new DAException(E.getMessage());
        }

    }

    @Override
    public void delete(Member t) {
        if (t == null) throw new NullPointerException("member is null");

        try {
            Member mem = entityManager.merge(t);
            entityManager.remove(mem);
        } catch (RuntimeException E) {
            throw new DAException(E.getMessage());
        }
    }

    @Override
    public Member find(Member t) {
        if (t == null) throw new NullPointerException("member is null");

        try {
            final Query query = entityManager.createQuery("SELECT mem FROM Member as mem WHERE mem.idMember = :id");
            query.setParameter("id", t.getIdMember());
            return (Member) query.getSingleResult();
        } catch (RuntimeException E) {
            throw new DAException(E.getMessage());
        }
    }
}