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

/**
 *
 * @author Boris Valentovic - xvalent2
 */
public interface PrintedBookDAO {
    
    /**
     * Find printed book by id
     * @param id Id of wanted printed printed book
     * @return Instance of wanted printed book
     */
    public PrintedBook findPrintedBookById(long id);
    
    /**
     * Find all printed books of Book from database 
     * @param book Book
     * @return List of all printed books of book
     */
    public List<PrintedBook> findPrintedBooks(Book book);
    
    /**
     * Find all printed books of book which are loaned or not loaned
     * @param book Book
     * @param state Boolean.TRUE for loaned, Boolean.FALSE for not loaned
     * @return List of all printed books of book according to state
     */
    public List<PrintedBook> findPrintedBooksByState(Book book, Boolean state);
    
    /**
     * Find all printed books of book in loan
     * @param book Book
     * @param loan Loan
     * @return List of all printed books of book in specific loan
     */
    public List<PrintedBook> findPrintedBooksByLoan(Book book, Loan loan);
    
    /**
     * Find all printed books in specific loan
     * @param loan Loan which printed books we look for
     * @return List of all printed books in specific loan
     */
    public List<PrintedBook> findAllPrintedBooksByLoan(Loan loan);
    
    /**
     * Find all borrowed printed books
     * @return List of all borrowed printed books
     */
    public List<PrintedBook> findAllBorrowedPrintedBooks();

}
