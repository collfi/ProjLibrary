package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.entity.Book;
import cz.fi.muni.pa165.entity.Loan;
import cz.fi.muni.pa165.entity.Member;

import java.util.Date;
import java.util.List;

/**
 * Created by sergii on 11.10.14.
 */
public interface ILoanDAO {

    public List<Loan> FindAllLoansByMember(Member m, boolean is_returned);
    public List<Loan> FindAllLoandsFromTo(Date from, Date to);
    public List<Loan> FindAllLoansWithBook(Book b);

}
