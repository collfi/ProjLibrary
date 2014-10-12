package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.entity.Book;
import cz.fi.muni.pa165.entity.PrintedBook;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

/**
 * Dao Implementation of DAO interface for book.
 * 
 * @author Michal Lukac, xlukac, 430614
 */
public class BookDAO implements IBookDAO, IGenericDAO<Book> {
    
    @PersistenceContext(unitName = "book-unit", type = PersistenceContextType.EXTENDED)
    private EntityManager entityManager;
    
    /**
     * Sets the entity manager
     * @param entityManager 
     */
    @Override
    public void setManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Insert book to the book table.
     * @param t book
     */
    @Override
    public void insert(Book t) {
        if (t == null) {
            throw new IllegalArgumentException("cannot update null in book");
        }
        entityManager.persist(t);
    }

    /**
     * Update book in table.
     * @param t book
     */
    @Override
    public void update(Book t) {
        if (t == null) {
            throw new IllegalArgumentException("cannot insert null in book");
        }

        //toto mas dobre??
        Book book = (Book)entityManager.find(Book.class ,t.getId());
        book.setName(t.getName());
        book.setAuthors(t.getAuthors());
        book.setDescription(t.getDescription());
        book.setISBN(t.getISBN());
        book.setPrintedBooks(t.getPrintedBooks());
        entityManager.persist(book);
    }

    /**
     * Delete book in table.
     * @param t book
     */
    @Override
    public void delete(Book t) {
        if (t == null) {
            throw new IllegalArgumentException("cannot delete null in book");
        }

        Book a = entityManager.merge(t);
        
        PrintedBookDAO bdao = new PrintedBookDAO();
        bdao.setManager(entityManager);
        List<PrintedBook> books = bdao.findPrintedBooks(a);
        for(PrintedBook pb : books)
        {
            bdao.delete(pb);
        }
        entityManager.remove(a);
    }

    /**
     * Find book in table.
     * @param t book
     * @return returned book.
     */
    @Override
    public Book find(Book t) {
        if (t == null) {
            throw new IllegalArgumentException("cannot find null in book");
        }

        final Query query = entityManager.createQuery("SELECT m FROM Book as m WHERE m.idBook = :book");
        query.setParameter("book", t.getId());
        return (Book) query.getSingleResult();
    }

    /**
     * Find all books with specified ISBN number.
     * @param Isbn the unique number of book
     * @return List of books
     */
    @Override
    public List<Book> findBooksByISBN(String Isbn) {
        if (Isbn == null) {
            throw new IllegalArgumentException("cannot findisbn null in book");
        }

        final Query query = entityManager.createQuery("SELECT m FROM Book as m WHERE m.ISBN = :book");
        query.setParameter("book", Isbn);
        return query.getResultList();
    }


    /**
     * Find all books with name of the author
     * @param Author the name of author
     * @return List of books
     */
    @Override
    public List<Book> findBooksByAuthor(String Author) {
        if (Author == null) {
            throw new IllegalArgumentException("cannot insert null in book");
        }

        final Query query = entityManager.createQuery("SELECT m FROM Book as m WHERE m.authors like :book");
        query.setParameter("book", "%" + Author + "%");
        return query.getResultList();
    }

    /**
     * Find all Books with specified Department
     * @param Department which belongs the book
     * @return List of books
     */
    @Override
    public List<Book> findBooksByDepartment(Book.Department en) {
        if (en == null) {
            throw new IllegalArgumentException("cannot findbooksdepartment with null in book");
        }

        final Query query = entityManager.createQuery("SELECT m FROM Book as m WHERE m.department = :book");
        query.setParameter("book", en);
        return query.getResultList();
    }

    /**
     * Find all Books with specified name.
     * @param Name name of book
     * @return List of books
     */
    @Override
    public List<Book> findBooksByName(String Name) {
        if (Name == null) {
            throw new IllegalArgumentException("cannot findBooksByName with null in book");
        }

        final Query query = entityManager.createQuery("SELECT m FROM Book as m WHERE m.name like :name").setParameter("name", "%" + Name + "%");
        return query.getResultList();
    }
    
}
