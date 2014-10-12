package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.entity.Book;
import cz.fi.muni.pa165.entity.Loan;
import cz.fi.muni.pa165.entity.Member;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

/**
 * Created by sergii
 */
public class LoanDAO implements ILoanDAO, IGenericDAO<Loan> {

    //TODO get known
    @PersistenceContext(unitName = "loan-unit", type = PersistenceContextType.EXTENDED)
    private EntityManager em;

    @Override
    public List<Loan> FindAllLoansByMember(Member m, boolean is_returned) {
        final Query query = em.createQuery("SELECT l FROM Loan as l WHERE Loan.member.id = :mid and Loan.isReturned = :r");
        query.setParameter("mid", m.getIdMember());
        query.setParameter("r", is_returned);
        return  query.getResultList();
    }

    @Override
    public List<Loan> FindAllLoandsFromTo(Date from, Date to) {
        final Query query = em.createQuery("SELECT l FROM Loan as l WHERE l.fromDate > :f and l.fromDate < :t");
        query.setParameter("f", from);
        query.setParameter("t", to);
        return  query.getResultList();
    }

    @Override
    public List<Loan> FindAllLoansWithBook(Book b) {
//        final Query query = em.createQuery("SELECT m FROM PrintedBook as m WHERE m.book.idBook = :i");
//
//
//        final Query query = em.createQuery("SELECT l FROM Loan as l WHERE l.p in (" +
//                "SELECT m FROM PrintedBook as m WHERE m.book.idBook = :i)");
//        final Query query = em.createQuery("SELECT l FROM Loan as l WHERE :b in (l.pbooks)");
//        query.setParameter("b", b);
//        return  query.getResultList();
        return null;
    }

    @Override
    public void setManager(EntityManager entityManager) {
        this.em = entityManager;
    }

    @Override
    public void insert(Loan loan) {

    }

    @Override
    public void update(Loan loan) {

    }

    @Override
    public void delete(Loan loan) {

    }

    @Override
    public Loan find(Loan loan) {
        return null;
    }
}
