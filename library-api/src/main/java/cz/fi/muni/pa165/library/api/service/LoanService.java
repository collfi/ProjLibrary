package cz.fi.muni.pa165.library.api.service;

import cz.fi.muni.pa165.library.api.dto.BookDTO;
import cz.fi.muni.pa165.library.api.dto.LoanDTO;
import cz.fi.muni.pa165.library.api.dto.MemberDTO;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.Date;
import java.util.List;

/**
 * Interface for LoanService.
 *
 * @author Sergii Pylypenko - xpylypen, 430519
 */
@PreAuthorize("hasRole('ROLE_ADMIN')")
public interface LoanService {

    /**
     * Insert Loan to db.
     *
     * @param Loanto which will be inserted.
     */
    public void insertLoan(LoanDTO Loanto);

    /**
     * Update Loan.
     *
     * @param Loanto Update of the object.
     */
    public void updateLoan(LoanDTO Loanto);

    /**
     * Delete Loan from database.
     *
     * @param Loanto object which we want to delete.
     */
    public void deleteLoan(LoanDTO Loanto);

    /**
     * Find loan by unique id.
     *
     * @param id of loan.
     * @return LoanDTO
     */
    public LoanDTO findLoanById(int id);

    /**
     * Find Loan in database.
     *
     * @param Loanto Loan which we are trying to find.
     * @return LoanDTO from db
     */
    public LoanDTO findLoan(LoanDTO Loanto);

    /**
     * Find all Loans with specified member.
     *
     * @param member      member loan belongs to
     * @param is_returned if loan is closed
     * @return List of Loans
     */
    public List<LoanDTO> findAllLoansByMember(MemberDTO member, boolean is_returned);

    /**
     * Find all Loans with in specific date range.
     *
     * @param from date from loan was created
     * @param to   date to loan was created
     * @return List of Loans
     */
    public List<LoanDTO> findAllLoandsFromTo(Date from, Date to);

    /**
     * Find all Loans with specified book
     *
     * @param b book specified in loan
     * @return List of Loans
     */
    public List<LoanDTO> findAllLoansWithBook(BookDTO b);

    public List<LoanDTO> findAllLoans();

}
