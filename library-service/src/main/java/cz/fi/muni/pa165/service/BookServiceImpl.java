/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.dao.BookDAO;
import cz.fi.muni.pa165.dao.LoanDAO;
import cz.fi.muni.pa165.dao.PrintedBookDAO;
import cz.fi.muni.pa165.datatransferobject.BookDTO;
import cz.fi.muni.pa165.entity.Book;
import cz.fi.muni.pa165.service.api.BookService;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author michal
 */
@Service
@Transactional
public class BookServiceImpl implements BookService {

    @Autowired
    private PrintedBookDAO pbookDao;
    
    @Autowired
    private BookDAO bookDao;
    
    @Override
    public void insertBook(BookDTO pbookto) {
        Book b = DTOEntityManager.bookDTOtoEntity(pbookto);
        bookDao.insert(b);
    }

    @Override
    public BookDTO findBook(BookDTO pbookto) {
        Book b = DTOEntityManager.bookDTOtoEntity(pbookto);
        return DTOEntityManager.bookEntitytoDTO(bookDao.find(b));
    }

    @Override
    public BookDTO findBookById(long id) {
        Book b = bookDao.findBookById(id);
        return DTOEntityManager.bookEntitytoDTO(b);
    }
}
