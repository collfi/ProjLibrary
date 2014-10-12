package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.entity.Book;
import cz.fi.muni.pa165.entity.Loan;
import cz.fi.muni.pa165.entity.Member;
import cz.fi.muni.pa165.entity.PrintedBook;
import org.testng.annotations.Test;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.testng.Assert.assertEquals;

/**
 * Created by sergii
 */

//TODO do we need some kind of transaction support here?
public class LoanDAO implements ILoanDAO, IGenericDAO<Loan> {

    @PersistenceContext(unitName = "loan-unit", type = PersistenceContextType.EXTENDED)
    private EntityManager em;

    @Override
    public List<Loan> FindAllLoansByMember(Member m, boolean is_returned) {
        final TypedQuery<Loan> query = em.createQuery(
                "SELECT l FROM Loan as l WHERE Loan.member.id = :mid and Loan.isReturned = :r", Loan.class);
        query.setParameter("mid", m.getIdMember());
        query.setParameter("r", is_returned);
        return query.getResultList();
    }

    @Override
    public List<Loan> FindAllLoandsFromTo(Date from, Date to) {
        final TypedQuery<Loan> query = em.createQuery(
                "SELECT l FROM Loan as l WHERE l.fromDate > :f and l.fromDate < :t", Loan.class);
        query.setParameter("f", from);
        query.setParameter("t", to);
        return query.getResultList();
    }

    @Override
    public List<Loan> FindAllLoansWithBook(Book b) {
        final TypedQuery<Loan> query = em.createQuery("SELECT l from Loan as l WHERE l.id IN " +
                "(SELECT pb.loan.id from PrintedBook as pb where pb.book.id = :bid)", Loan.class);
        query.setParameter("bid", b.getId());
        return query.getResultList();
    }

    @Override
    public void setManager(EntityManager entityManager) {
        this.em = entityManager;
    }

    @Override
    public void insert(Loan l) {
        this.em.persist(l);
    }

    @Override
    public void update(Loan loan) {
        Loan l = find(loan);
        l.setReturned(l.isReturned());
        l.setWhen(l.getWhen());
        l.setFromDate(l.getFromDate());
        l.setToDate(l.getToDate());
        l.setDescription(l.getDescription());
        l.setMember(l.getMember());
        l.setPbooks(l.getPbooks());
        em.persist(l);
    }

    @Override
    public void delete(Loan loan) {
        //TODO what about transactions
//
//        em.getTransaction().begin();
//        em.remove(loan);
//        em.getTransaction().commit();

        Loan l = this.em.merge(loan);
        this.em.remove(l);
    }

    //TODO case with no result
    @Override
    public Loan find(Loan loan) {
        CriteriaBuilder cb  = em.getCriteriaBuilder();
        CriteriaQuery<Loan> cq = cb.createQuery(Loan.class);
        Root<Loan> root = cq.from(Loan.class);
        cq.select(root);
        TypedQuery<Loan> q = em.createQuery(cq);
        return q.getSingleResult();
    }



}
