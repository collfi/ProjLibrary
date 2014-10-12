/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.entity.Book;
import cz.fi.muni.pa165.entity.Loan;
import cz.fi.muni.pa165.entity.PrintedBook;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Boris Valentovic - xvalent2
 */
public class PrintedBookDAO implements IPrintedBookDAO, IGenericDAO<PrintedBook> {

    @PersistenceContext(unitName = "pbook-unit", type = PersistenceContextType.EXTENDED)
    private EntityManager em;

    @Override
    public List<PrintedBook> findPrintedBooks(Book book) {
        final Query query = em.createQuery("SELECT m FROM PrintedBook as m WHERE m.book.idBook = :i");
        query.setParameter("i", book.getId());
        return query.getResultList();
    }

    @Override
    public List<PrintedBook> findPrintedBooksByState(Book book, Boolean state) {
        final Query query = em.createQuery("SELECT m FROM PrintedBook as m WHERE m.book.idBook = :i AND m.state = :s");
        query.setParameter("i", book.getId());
        query.setParameter("s", state);
        return query.getResultList();
    }

    @Override
    public void setManager(EntityManager entityManager) {
        this.em = entityManager;
    }

    @Override
    public void insert(PrintedBook t) {
        em.persist(t);
    }

    @Override
    public void update(PrintedBook printedBook) {
        if (printedBook == null) {
            throw new IllegalArgumentException("printed book in update is null");
        }

        PrintedBook pb = find(printedBook);

        if (pb == null) {
            throw new IllegalArgumentException("printed book after find is null");
        }
        
        pb.setBook(printedBook.getBook());
        pb.setCondition(printedBook.getCondition());
        pb.setState(printedBook.getState());
        em.persist(pb);
    }

    @Override
    public void delete(PrintedBook t) {
        PrintedBook a = em.merge(t);
        em.remove(a);
    }

    @Override
    public PrintedBook find(PrintedBook t) {
        final Query query = em.createQuery("SELECT m FROM PrintedBook as m WHERE m.idPrintedBook = :i");
        query.setParameter("i", t.getIdPrintedBook());
        return (PrintedBook) query.getSingleResult();
    }

    @Override
    public PrintedBook findPrintedBookById(int id) {
        final Query query = em.createQuery("SELECT m FROM PrintedBook as m WHERE m.idPrintedBook = :i");
        query.setParameter("i", id);
        return (PrintedBook) query.getSingleResult();
    }

    @Override
    public List<PrintedBook> findAllBorrowedPrintedBooks() {
        final Query query = em.createQuery("SELECT m FROM PrintedBook as m WHERE m.state = :i");
        query.setParameter("i", Boolean.TRUE);
        return query.getResultList();
    }

    @Override
    public List<PrintedBook> findAllPrintedBooksByLoan(Loan loan) {
        final TypedQuery<PrintedBook> query = em.createQuery(
                "SELECT m FROM PrintedBook as m WHERE m.loan.idLoan = :lid", PrintedBook.class);
        query.setParameter("lid", loan.getIdLoan());
        return query.getResultList();
    }

    @Override
    public List<PrintedBook> findPrintedBooksByLoan(Book book, Loan loan) {
        final Query query = em.createQuery("SELECT m FROM PrintedBook as m WHERE m.book.idBook = :i AND m.loan.idLoan = :l");
        query.setParameter("i", book.getId());
        query.setParameter("l", loan.getIdLoan());
        return query.getResultList();
    }

}
