package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.DAException;
import cz.fi.muni.pa165.entity.Book;
import cz.fi.muni.pa165.entity.Loan;
import cz.fi.muni.pa165.entity.Member;
import cz.fi.muni.pa165.entity.PrintedBook;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;

import static org.testng.Assert.assertEquals;

/**
* @author Pylypenko Sergii <430519@mail.muni.cz>
 */

public class LoanDAOImpl implements LoanDAO, GenericDAO<Loan> {

    @PersistenceContext(unitName = "loan-unit", type = PersistenceContextType.EXTENDED)
    private EntityManager em;

    @Override
    public List<Loan> findAllLoansByMember(Member m, boolean is_returned) {
        try {
            final TypedQuery<Loan> query = em.createQuery(
                    "SELECT l FROM Loan as l WHERE l.member.id = :mid and l.isReturned = :r", Loan.class);
            query.setParameter("mid", m.getIdMember());
            query.setParameter("r", is_returned);
            return query.getResultList();
        } catch(RuntimeException E) {
            throw new DAException(E.getMessage());
        }
    }

    @Override
    public List<Loan> findAllLoandsFromTo(Date from, Date to) {
        try {
            final TypedQuery<Loan> query = em.createQuery(
                    "SELECT l FROM Loan as l WHERE l.fromDate > :f and l.fromDate < :t", Loan.class);
            query.setParameter("f", from);
            query.setParameter("t", to);
            return query.getResultList();
        } catch(RuntimeException E) {
            throw new DAException(E.getMessage());
        }
    }

    @Override
    public List<Loan> findAllLoansWithBook(Book b) {
        try {
            final TypedQuery<Loan> query = em.createQuery("SELECT l from Loan as l WHERE l.id IN " +
                    "(SELECT pb.loan.id from PrintedBook as pb where pb.book.id = :bid)", Loan.class);
            query.setParameter("bid", b.getId());
            return query.getResultList();
        } catch(RuntimeException E) {
            throw new DAException(E.getMessage());
        }
    }

    @Override
    public void setManager(EntityManager entityManager) {
        this.em = entityManager;
    }

    @Override
    public void insert(Loan l) {
        try {
            this.em.persist(l);
        } catch(RuntimeException E) {
            throw new DAException(E.getMessage());
        }
    }

    @Override
    public void update(Loan loan) {
        try {
            Loan l = find(loan);
            l.setReturned(l.isReturned());
            l.setDateReturned(l.getDateReturned());
            l.setFromDate(l.getFromDate());
            l.setToDate(l.getToDate());
            l.setDescription(l.getDescription());
            l.setMember(l.getMember());
            l.setPbooks(l.getPbooks());
            em.persist(l);
        } catch(RuntimeException E) {
            throw new DAException(E.getMessage());
        }
    }

    @Override
    public void delete(Loan loan) {
        try {
            PrintedBookDAOImpl pbd = new PrintedBookDAOImpl();
            pbd.setManager(em);
            List<PrintedBook> books = pbd.findAllPrintedBooksByLoan(loan);
            for (PrintedBook pb : books){
                pb.setLoan(null);
            }
            em.remove(loan);
        } catch(RuntimeException E) {
            throw new DAException(E.getMessage());
        }
    }

    @Override
    public Loan find(Loan loan) {
        try {
            CriteriaBuilder cb  = em.getCriteriaBuilder();
            CriteriaQuery<Loan> cq = cb.createQuery(Loan.class);
            Root<Loan> root = cq.from(Loan.class);
            cq.select(root);
            TypedQuery<Loan> q = em.createQuery(cq);
            return q.getSingleResult();
        } catch(Exception E) {
            throw new DAException(E.getMessage());
        }
    }
}
