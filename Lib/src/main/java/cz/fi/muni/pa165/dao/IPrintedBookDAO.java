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
public interface IPrintedBookDAO {
    
    public PrintedBook findPrintedBookById(int id);
    
    public List<PrintedBook> findPrintedBooks(Book book);
    
    public List<PrintedBook> findPrintedBooksByState(Book book, Boolean state);
    
    public List<PrintedBook> findPrintedBooksByLoan(Book book, Loan loan);
    
    public List<PrintedBook> findAllPrintedBooksByLoan(Loan loan);
    
    public List<PrintedBook> findAllBorrowedPrintedBooks();

}
