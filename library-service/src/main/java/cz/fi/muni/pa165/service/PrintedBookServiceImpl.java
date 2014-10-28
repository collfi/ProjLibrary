/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.dao.BookDAO;
import cz.fi.muni.pa165.dao.LoanDAO;
import cz.fi.muni.pa165.dao.PrintedBookDAO;
import cz.fi.muni.pa165.entity.PrintedBook;
import cz.fi.muni.pa165.service.api.PrintedBookService;
import cz.fi.muni.pa165.datatransferobject.BookDTO;
import cz.fi.muni.pa165.datatransferobject.LoanDTO;
import cz.fi.muni.pa165.datatransferobject.PrintedBookDTO;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Boris Valentovic - xvalent2
 */
@Service
@Transactional
public class PrintedBookServiceImpl implements PrintedBookService {
    
    @Autowired
    private PrintedBookDAO pbookDao;
    
    @Autowired
    private BookDAO bookDao;
    
    @Autowired
    private LoanDAO loanDao;
    
    public void setPrintedBookDao(PrintedBookDAO pbookDao) {
        this.pbookDao = pbookDao;
    }
    
    public void setBookDao(BookDAO bookDao) {
        this.bookDao = bookDao;
    }
    
    public void setLoanDao(LoanDAO loanDao) {
        this.loanDao = loanDao;
    }

    @Override
    public PrintedBookDTO insertPrintedBook(PrintedBookDTO pbookto) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public PrintedBookDTO updatePrintedBook(PrintedBookDTO pbookto) {
        throw new UnsupportedOperationException("Not supported yet.");
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PrintedBookDTO deletePrintedBook(PrintedBookDTO pbookto) {
        throw new UnsupportedOperationException("Not supported yet.");
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PrintedBookDTO findPrintedBookByBook(BookDTO bookto) {
        throw new UnsupportedOperationException("Not supported yet.");
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PrintedBookDTO findPrintedBookByCondition(BookDTO bookto, PrintedBook.Condition con) {
        throw new UnsupportedOperationException("Not supported yet.");
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PrintedBookDTO findPrintedBookByState(BookDTO bookto, Boolean state) {
        throw new UnsupportedOperationException("Not supported yet.");
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PrintedBookDTO findPrintedBookById(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PrintedBookDTO> findAllPrintedBooksByLoan(LoanDTO loanto) {
        throw new UnsupportedOperationException("Not supported yet.");
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PrintedBookDTO> findAllBorrowedPrintedBooks() {
        throw new UnsupportedOperationException("Not supported yet.");
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PrintedBookDTO> findPrintedBooksByLoan(BookDTO bookto, LoanDTO loanto) {
        throw new UnsupportedOperationException("Not supported yet.");
        //To change body of generated methods, choose Tools | Templates.
    }
    
}
