/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.service.api;

import cz.fi.muni.pa165.entity.Book;
import cz.fi.muni.pa165.entity.PrintedBook.Condition;

import cz.fi.muni.pa165.datatransferobject.BookDTO;
import cz.fi.muni.pa165.datatransferobject.LoanDTO;
import cz.fi.muni.pa165.datatransferobject.PrintedBookDTO;
import java.util.List;


/**
 *
 * @author Boris Valentovic - xvalent2
 */
public interface PrintedBookService {
    public void insertPrintedBook(PrintedBookDTO pbookto);
    
    public void updatePrintedBook(PrintedBookDTO pbookto);
    
    public void deletePrintedBook(PrintedBookDTO pbookto);
    
    public PrintedBookDTO findPrintedBook(PrintedBookDTO pbookto);
    
    public List<PrintedBookDTO> findPrintedBooksByBook(BookDTO bookto);
    
    //public PrintedBookDTO findPrintedBookByCondition(BookDTO bookto, Condition con);
    
    public List<PrintedBookDTO> findPrintedBookByState(BookDTO bookto, Boolean state);
    
    public PrintedBookDTO findPrintedBookById(Long id);
    
    public List<PrintedBookDTO> findAllPrintedBooksByLoan(LoanDTO loanto);
    
    public List<PrintedBookDTO> findAllBorrowedPrintedBooks();
    
    public List<PrintedBookDTO> findPrintedBooksByLoan(BookDTO bookto, LoanDTO loanto);
    
    
}
