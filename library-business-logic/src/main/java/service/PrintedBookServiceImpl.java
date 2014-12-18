/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import cz.fi.muni.pa165.dao.BookDAO;
import cz.fi.muni.pa165.dao.LoanDAO;
import cz.fi.muni.pa165.dao.PrintedBookDAO;
import cz.fi.muni.pa165.entity.Book;
import cz.fi.muni.pa165.entity.Loan;
import cz.fi.muni.pa165.entity.PrintedBook;
import cz.fi.muni.pa165.library.api.dto.BookDTO;
import cz.fi.muni.pa165.library.api.dto.LoanDTO;
import cz.fi.muni.pa165.library.api.dto.PrintedBookDTO;
import cz.fi.muni.pa165.library.api.service.PrintedBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Service layer class of Printed Book implementation
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
    public PrintedBookDTO findPrintedBook(PrintedBookDTO pbookto) {
        PrintedBook pbook = DTOEntityManager.printedBookDTOtoEntity(pbookto);
        PrintedBookDTO pbdto = DTOEntityManager.printedBookEntitytoDTO(pbookDao.find(pbook));
        return pbdto;
    }

    @Override
    public List<PrintedBookDTO> findPrintedBooksByBook(BookDTO bookto) {
        Book book = bookDao.find(DTOEntityManager.bookDTOtoEntity(bookto));
        List<PrintedBook> pbooks = pbookDao.findPrintedBooks(book);
        List<PrintedBookDTO> pbooksdto = new ArrayList<>();
        for (PrintedBook pb : pbooks) {
            pbooksdto.add(DTOEntityManager.printedBookEntitytoDTO(pb));
        }
        return pbooksdto;
    }

    @Override
    public List<PrintedBookDTO> findPrintedBookByState(BookDTO bookto, Boolean state) {
        Book book = DTOEntityManager.bookDTOtoEntity(bookto);
        List<PrintedBook> pbooks = pbookDao.findPrintedBooksByState(book, state);
        List<PrintedBookDTO> pbooksdto = new ArrayList<>();
        for (PrintedBook pb : pbooks) {
            pbooksdto.add(DTOEntityManager.printedBookEntitytoDTO(pb));
        }
        return pbooksdto;

    }

    @Override
    public PrintedBookDTO findPrintedBookById(Long id) {
        PrintedBook pb = pbookDao.findPrintedBookById(id);
        return DTOEntityManager.printedBookEntitytoDTO(pb);
    }

    @Override
    public List<PrintedBookDTO> findAllPrintedBooksByLoan(LoanDTO loanto) {
        Loan l = loanDao.find(DTOEntityManager.loanDTOtoEntity(loanto));
        List<PrintedBookDTO> pbooksdto = new ArrayList<>();
        for (PrintedBook pb : pbookDao.findAllPrintedBooksByLoan(l)) {
            pbooksdto.add(DTOEntityManager.printedBookEntitytoDTO(pb));
        }
        return pbooksdto;
    }

    @Override
    public List<PrintedBookDTO> findAllBorrowedPrintedBooks() {
        List<PrintedBookDTO> result = new ArrayList<>();
        for (PrintedBook pb : pbookDao.findAllBorrowedPrintedBooks()) {
            result.add(DTOEntityManager.printedBookEntitytoDTO(pb));
        }
        return result;
    }

    @Override
    public List<PrintedBookDTO> findPrintedBooksByLoan(BookDTO bookto, LoanDTO loanto) {
        Loan l = loanDao.find(DTOEntityManager.loanDTOtoEntity(loanto));
        Book b = bookDao.find(DTOEntityManager.bookDTOtoEntity(bookto));
        List<PrintedBookDTO> result = new ArrayList<>();
        for (PrintedBook pbook : pbookDao.findPrintedBooksByLoan(b, l)) {
            result.add(DTOEntityManager.printedBookEntitytoDTO(pbook));
        }
        return result;
    }


}
