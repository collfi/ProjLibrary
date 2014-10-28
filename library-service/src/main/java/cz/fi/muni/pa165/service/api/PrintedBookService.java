/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.service.api;

import cz.fi.muni.pa165.entity.Book;
import cz.fi.muni.pa165.entity.PrintedBook.Condition;

import cz.fi.muni.pa165.transferobject.BookTO;
import cz.fi.muni.pa165.transferobject.LoanTO;
import cz.fi.muni.pa165.transferobject.PrintedBookTO;
import java.util.List;


/**
 *
 * @author Boris Valentovic - xvalent2
 */
public interface PrintedBookService {
    public PrintedBookTO insertPrintedBook(PrintedBookTO pbookto);
    
    public PrintedBookTO updatePrintedBook(PrintedBookTO pbookto);
    
    public PrintedBookTO deletePrintedBook(PrintedBookTO pbookto);
    
    public PrintedBookTO findPrintedBookByBook(BookTO bookto);
    
    public PrintedBookTO findPrintedBookByCondition(BookTO bookto, Condition con);
    
    public PrintedBookTO findPrintedBookByState(BookTO bookto, Boolean state);
    
    public PrintedBookTO findPrintedBookById(Long id);
    
    public List<PrintedBookTO> findAllPrintedBooksByLoan(LoanTO loanto);
    
    public List<PrintedBookTO> findAllBorrowedPrintedBooks();
    
    public List<PrintedBookTO> findPrintedBooksByLoan(BookTO bookto, LoanTO loanto);
    
    
}
