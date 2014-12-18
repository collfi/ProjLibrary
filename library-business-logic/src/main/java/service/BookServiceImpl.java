/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import cz.fi.muni.pa165.dao.BookDAO;
import cz.fi.muni.pa165.entity.Book;
import cz.fi.muni.pa165.library.api.constants.Department;
import cz.fi.muni.pa165.library.api.dto.BookDTO;
import cz.fi.muni.pa165.library.api.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Implemented book service for data transfer object.
 * Implementation with dependency injection.(auto-wired)
 *
 * @author michal.lukac, xlukac, 430614
 */
@Service
@Transactional
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDAO bookDao;

    public void setBookDAO(BookDAO bookdao) {
        this.bookDao = bookdao;
    }

    @Override
    public void insertBook(BookDTO pbookto) {
        Book b = DTOEntityManager.bookDTOtoEntity(pbookto);
        bookDao.insert(b);
    }

    @Override
    public BookDTO findBook(BookDTO bookto) {
        Book b = DTOEntityManager.bookDTOtoEntity(bookto);
        return DTOEntityManager.bookEntitytoDTO(bookDao.find(b));
    }

    @Override
    public BookDTO findBookById(long id) {
        Book b = bookDao.findBookById(id);
        return DTOEntityManager.bookEntitytoDTO(b);
    }

    @Override
    public void updateBook(BookDTO bookto) {
        Book b = DTOEntityManager.bookDTOtoEntity(bookto);
        bookDao.update(b);
    }

    @Override
    public void deleteBook(BookDTO bookto) {
        Book b = DTOEntityManager.bookDTOtoEntity(bookto);
        bookDao.delete(b);
    }

    @Override
    public List<BookDTO> findBooksByISBN(String Isbn) {
        List<Book> books = bookDao.findBooksByISBN(Isbn);
        List<BookDTO> booksdto = new ArrayList<BookDTO>();
        for (Book b : books) {
            booksdto.add(DTOEntityManager.bookEntitytoDTO(b));
        }
        return booksdto;
    }

    @Override
    public List<BookDTO> findBooksByAuthor(String Author) {
        List<Book> books = bookDao.findBooksByAuthor(Author);
        List<BookDTO> booksdto = new ArrayList<BookDTO>();
        for (Book b : books) {
            booksdto.add(DTOEntityManager.bookEntitytoDTO(b));
        }
        return booksdto;
    }

    @Override
    public List<BookDTO> findBooksByDepartment(Department en) {
        List<Book> books = bookDao.findBooksByDepartment(en);
        List<BookDTO> booksdto = new ArrayList<BookDTO>();
        for (Book b : books) {
            booksdto.add(DTOEntityManager.bookEntitytoDTO(b));
        }
        return booksdto;
    }

    @Override
    public List<BookDTO> findBooksByName(String Name) {
        List<Book> books = bookDao.findBooksByName(Name);
        List<BookDTO> booksdto = new ArrayList<BookDTO>();
        for (Book b : books) {
            booksdto.add(DTOEntityManager.bookEntitytoDTO(b));
        }
        return booksdto;
    }

    @Override
    public List<BookDTO> findAllBooks() {
        List<Book> books = bookDao.findAllBooks();
        List<BookDTO> booksdto = new ArrayList<BookDTO>();
        for (Book b : books) {
            booksdto.add(DTOEntityManager.bookEntitytoDTO(b));
        }
        return booksdto;
    }
}
