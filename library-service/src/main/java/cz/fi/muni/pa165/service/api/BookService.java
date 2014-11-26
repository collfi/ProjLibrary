package cz.fi.muni.pa165.service.api;

import cz.fi.muni.pa165.datatransferobject.BookDTO;
import cz.fi.muni.pa165.entity.Book.Department;
import java.util.List;

/**
 * Interface for BookService.
 * @author michal.lukac - xlukac, 430614
 */
public interface BookService {
    
    /**
     * Insert book to db.
     * @param bookto which will be inserted.
     */
    public void insertBook(BookDTO bookto);
    
    /**
     * Update Book.
     * @param bookto Update of the object.
     */
    public void updateBook(BookDTO bookto);
    
    /**
     * Delete Book from database.
     * @param bookto object which we want to delete.
     */
    public void deleteBook(BookDTO bookto);
    
    /**
     * Find Book in database.
     * @param bookto book which we are trying to find.
     * @return BookDTO from db
     */
    public BookDTO findBook(BookDTO bookto);  
    
    /**
     * Find book by unique id.
     * @param id of book.
     * @return BookDTO
     */
    public BookDTO findBookById(long id);
    
    /**
     * Find all books with specified ISBN number.
     * @param Isbn the unique number of book
     * @return List of books
     */
    public List<BookDTO> findBooksByISBN(String Isbn);
    
    /**
     * Find all books with name of the author
     * @param Author the name of author
     * @return List of books
     */
    public List<BookDTO> findBooksByAuthor(String Author);
    
    /**
     * Find all Books with specified Department
     * @param Department which belongs the book
     * @return List of books
     */
    public List<BookDTO> findBooksByDepartment(Department en);
    
    /**
     * Find all Books with specified name.
     * @param Name name of book
     * @return List of books
     */
    public List<BookDTO> findBooksByName(String Name);
    
    public List<BookDTO> findAllBooks();

}
