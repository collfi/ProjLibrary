package cz.fi.muni.pa165.dao;

import java.util.Date;
import java.util.List;

import cz.fi.muni.pa165.entity.Book;
import cz.fi.muni.pa165.entity.Loan;
import cz.fi.muni.pa165.entity.Member;

/**
 * @author Pylypenko Sergii <430519@mail.muni.cz>
 */
 public interface LoanDAO extends GenericDAO<Loan>{

    /**
     * Searches loans from of specific member and loan state
     * @param m member to search loans by
     * @param is_returned if loan has been closed
     * @return list of found loans
     */
    public List<Loan> findAllLoansByMember(Member m, boolean is_returned);

    /**
     * Searches loans that have been created in specified date interval
     * @param from date from which loan has been created
     * @param to date until which loan has been created
     * @return list of found loans
     */
    public List<Loan> findAllLoandsFromTo(Date from, Date to);

    /**
     * Method searches all loans by specific book
     * @param b book to search by
     * @return list of found loans
     */
    public List<Loan> findAllLoansWithBook(Book b);

    /**
     * Find loan by specified id.
     * @param id of book
     * @return loan if find
     */
    public Loan findLoanById(long id);

    
    public List<Loan> findAllLoans();
}
