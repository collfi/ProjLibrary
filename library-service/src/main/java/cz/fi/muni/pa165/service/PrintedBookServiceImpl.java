/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.dao.BookDAO;
import cz.fi.muni.pa165.dao.LoanDAO;
import cz.fi.muni.pa165.dao.PrintedBookDAO;
import cz.fi.muni.pa165.dao.PrintedBookDAOImpl;
import cz.fi.muni.pa165.entity.PrintedBook;
import cz.fi.muni.pa165.service.api.PrintedBookService;
import cz.fi.muni.pa165.datatransferobject.BookDTO;
import cz.fi.muni.pa165.datatransferobject.LoanDTO;
import cz.fi.muni.pa165.datatransferobject.PrintedBookDTO;
import cz.fi.muni.pa165.entity.Book;
import java.util.ArrayList;
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
    private PrintedBookDAOImpl pbookDao;
    
    @Autowired
    private BookDAO bookDao;
    
    @Autowired
    private LoanDAO loanDao;
    
    public void setPrintedBookDao(PrintedBookDAOImpl pbookDao) {
        this.pbookDao = pbookDao;
    }
    
    public void setBookDao(BookDAO bookDao) {
        this.bookDao = bookDao;
    }
    
    public void setLoanDao(LoanDAO loanDao) {
        this.loanDao = loanDao;
    }

    @Override
    public void insertPrintedBook(PrintedBookDTO pbookto) {
        PrintedBook pbook = DTOEntityManager.printedBookDTOtoEntity(pbookto);
        pbookDao.insert(pbook);
    }

    @Override
    public void updatePrintedBook(PrintedBookDTO pbookto) {
        PrintedBook pbook = DTOEntityManager.printedBookDTOtoEntity(pbookto);
        pbookDao.update(pbook);
    }

    @Override
    public void deletePrintedBook(PrintedBookDTO pbookto) {
        PrintedBook pbook = DTOEntityManager.printedBookDTOtoEntity(pbookto);
        pbookDao.delete(pbook);
    }

    @Override
    public List<PrintedBookDTO> findPrintedBooksByBook(BookDTO bookto) {
        List<PrintedBook> pbooks;
        List<PrintedBookDTO> pbooksdto = new ArrayList<>();
        Book book = DTOEntityManager.bookDTOtoEntity(bookto); //bookdao.findBookByid(bookto.getId()) ??
        pbooks = pbookDao.findPrintedBooks(book);
        for (PrintedBook pb: pbooks) {
            pbooksdto.add(DTOEntityManager.printedBookEntitytoDTO(pb));
        }
        return pbooksdto;
    }

    /*@Override
    public PrintedBookDTO findPrintedBookByCondition(BookDTO bookto, PrintedBook.Condition con) {
        throw new UnsupportedOperationException("Not supported yet.");
        //To change body of generated methods, choose Tools | Templates.
    }*/

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
