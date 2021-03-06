package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.entity.Book;
import cz.fi.muni.pa165.entity.Loan;
import cz.fi.muni.pa165.entity.Member;
import cz.fi.muni.pa165.entity.PrintedBook;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;


/**
 * @author Pylypenko Sergii <430519@mail.muni.cz>
 */
@Component
public class LoanDAOImpl implements LoanDAO, GenericDAO<Loan> {

    @PersistenceContext(unitName = "myUnit")
    private EntityManager em;

    @Override
    public List<Loan> findAllLoans() {
        try {
            final TypedQuery<Loan> query = em.createQuery(
                    "SELECT l FROM Loan as l", Loan.class);
            return query.getResultList();
        } catch (RuntimeException E) {
            throw new DAException(E.getMessage());
        }
    }

    @Override
    public List<Loan> findAllLoansByMember(Member m, boolean is_returned) {
        try {
            final TypedQuery<Loan> query = em.createQuery(
                    "SELECT l FROM Loan as l WHERE l.member.id = :mid and l.returned = :r", Loan.class);
            query.setParameter("mid", m.getIdMember());
            query.setParameter("r", is_returned);
            return query.getResultList();
        } catch (RuntimeException E) {
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
        } catch (RuntimeException E) {
            throw new DAException(E.getMessage());
        }
    }

    @Override
    public List<Loan> findAllLoansWithBook(Book b) {
        try {
            final TypedQuery<Loan> query = em.createQuery("SELECT l from Loan as l WHERE l.printedBook.book.idBook = :bid)", Loan.class);
            query.setParameter("bid", b.getIdBook());
            return query.getResultList();
        } catch (RuntimeException E) {
            throw new DAException(E.getMessage());
        }
    }

    @Override
    public Loan findLoanById(int id) {
        try {
            final TypedQuery<Loan> query = em.createQuery("SELECT l from Loan as l WHERE l.id = :lid)", Loan.class);
            query.setParameter("lid", id);
            return query.getSingleResult();
        } catch (RuntimeException E) {
            throw new DAException(E.getMessage());
        }
    }

    public void setManager(EntityManager entityManager) {
        this.em = entityManager;
    }

    @Override
    public void insert(Loan l) {
        try {
            this.em.persist(l);
        } catch (RuntimeException E) {
            throw new DAException(E.getMessage());
        }
    }

    @Override
    public void update(Loan loan) {
        try {
            em.merge(loan);

        } catch (RuntimeException E) {
            throw new DAException(E.getMessage());
        }
    }

    @Override
    public void delete(Loan l) {
        try {
            Loan loan = (Loan) em.merge(l);
            PrintedBookDAOImpl pbd = new PrintedBookDAOImpl();
            pbd.setManager(em);
            List<PrintedBook> books = pbd.findAllPrintedBooksByLoan(loan);
            for (PrintedBook pb : books) {
                pb.setLoan(null);
            }
            em.remove(loan);
        } catch (RuntimeException E) {
            throw new DAException(E.getMessage());
        }
    }


    @Override
    public Loan find(Loan loan) {
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Loan> cq = cb.createQuery(Loan.class);
            Root<Loan> root = cq.from(Loan.class);
            cq.select(root);
            TypedQuery<Loan> q = em.createQuery(cq);
            return q.getSingleResult();
        } catch (Exception E) {
            throw new DAException(E.getMessage());
        }
    }
}
