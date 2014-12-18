package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.entity.Book;
import cz.fi.muni.pa165.library.api.constants.Department;

import java.util.List;

/**
 * DAO interface for Book
 *
 * @author michal.lukac, xlukac, 430614
 */
public interface BookDAO extends GenericDAO<Book> {

    /**
     * Find all books with specified ISBN number.
     *
     * @param Isbn the unique number of book
     * @return List of books
     */
    public List<Book> findBooksByISBN(String Isbn);

    /**
     * Find all books with name of the author
     *
     * @param Author the name of author
     * @return List of books
     */
    public List<Book> findBooksByAuthor(String Author);

    /**
     * Find all Books with specified Department
     *
     * @param Department which belongs the book
     * @return List of books
     */
    public List<Book> findBooksByDepartment(Department en);

    /**
     * Find all Books with specified name.
     *
     * @param Name name of book
     * @return List of books
     */
    public List<Book> findBooksByName(String Name);

    public List<Book> findAllBooks();

    /**
     * Find book by specified id.
     *
     * @param id of book
     * @return book if find
     */
    public Book findBookById(long id);


}
