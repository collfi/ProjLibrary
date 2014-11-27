package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.DAException;
import cz.fi.muni.pa165.entity.Book;
import cz.fi.muni.pa165.entity.PrintedBook;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import org.springframework.stereotype.Component;

/**
 * DAO Implementation of BookDAO interface.
 *
 * @author Michal Lukac, xlukac, 430614
 */
@Component
public class BookDAOImpl implements BookDAO {

    @PersistenceContext(unitName = "myUnit")
    private EntityManager entityManager;

    /**
     * Sets the entity manager
     *
     * @param entityManager
     */
    public void setManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Insert book to the book table.
     *
     * @param t book
     */
    @Override
    public void insert(Book t) {
        if (t == null) {
            throw new IllegalArgumentException("cannot update null in book");
        }

        try {
            entityManager.persist(t);
        } catch (RuntimeException E) {
            throw new DAException(E.getMessage());
        }
    }

    /**
     * Update book in table.
     *
     * @param t book
     */
    @Override
    public void update(Book t) {
        if (t == null) {
            throw new IllegalArgumentException("cannot insert null in book");
        }
        try {
            Book b = find(t);

            if (b == null) {
                throw new IllegalArgumentException("printed book after find is null");
            }
            b.setName(t.getName());
            b.setAuthors(t.getAuthors());
            b.setDapertment(t.getDepartment());
            b.setDescription(t.getDescription());
            b.setISBN(t.getISBN());
            entityManager.persist(b);
        } catch (RuntimeException E) {
            throw new DAException(E.getMessage());
        }
        /*try {
         Book book = (Book) entityManager.merge(t);
         } catch (RuntimeException E) {
         throw new DAException(E.getMessage());
         }*/
    }

    /**
     * Delete book in table.
     *
     * @param t book
     */
    @Override
    public void delete(Book t) {
        if (t == null) {
            throw new IllegalArgumentException("cannot delete null in book");
        }

        try {
            Book a = entityManager.merge(t);

            PrintedBookDAOImpl bdao = new PrintedBookDAOImpl();
            bdao.setManager(entityManager);
            List<PrintedBook> books = bdao.findPrintedBooks(a);
            for (PrintedBook pb : books) {
                bdao.delete(pb);
            }
            entityManager.remove(a);
        } catch (RuntimeException E) {
            throw new DAException(E.getMessage());
        }
    }

    /**
     * Find book in table.
     *
     * @param t book
     * @return returned book.
     */
    @Override
    public Book find(Book t) {
        if (t == null) {
            throw new IllegalArgumentException("cannot find null in book");
        }

        try {
            final Query query = entityManager.createQuery("SELECT m FROM Book as m WHERE m.idBook = :book");
            query.setParameter("book", t.getIdBook());
            return (Book) query.getSingleResult();
        } catch (RuntimeException E) {
            throw new DAException(E.getMessage());
        }
    }

    /**
     * Find all books with specified ISBN number.
     *
     * @param Isbn the unique number of book
     * @return List of books
     */
    @Override
    public List<Book> findBooksByISBN(String Isbn) {
        if (Isbn == null) {
            throw new IllegalArgumentException("cannot findisbn null in book");
        }

        try {
            final Query query = entityManager.createQuery("SELECT m FROM Book as m WHERE m.ISBN = :book");
            query.setParameter("book", Isbn);
            return query.getResultList();
        } catch (RuntimeException E) {
            throw new DAException(E.getMessage());
        }
    }

    /**
     * Find all books with name of the author
     *
     * @param Author the name of author
     * @return List of books
     */
    @Override
    public List<Book> findBooksByAuthor(String Author) {
        if (Author == null) {
            throw new IllegalArgumentException("cannot insert null in book");
        }

        try {
            final Query query = entityManager.createQuery("SELECT m FROM Book as m WHERE m.authors like :book");
            query.setParameter("book", "%" + Author + "%");
            return query.getResultList();
        } catch (RuntimeException E) {
            throw new DAException(E.getMessage());
        }
    }

    /**
     * Find all Books with specified Department
     *
     * @param Department which belongs the book
     * @return List of books
     */
    @Override
    public List<Book> findBooksByDepartment(Book.Department en) {
        if (en == null) {
            throw new IllegalArgumentException("cannot findbooksdepartment with null in book");
        }

        try {
            final Query query = entityManager.createQuery("SELECT m FROM Book as m WHERE m.department = :book");
            query.setParameter("book", en);
            return query.getResultList();
        } catch (RuntimeException E) {
            throw new DAException(E.getMessage());
        }
    }

    /**
     * Find all Books with specified name.
     *
     * @param Name name of book
     * @return List of books
     */
    @Override
    public List<Book> findBooksByName(String Name) {
        if (Name == null) {
            throw new IllegalArgumentException("cannot findBooksByName with null in book");
        }

        try {
            final Query query = entityManager.createQuery("SELECT m FROM Book as m WHERE m.name like :name").setParameter("name", "%" + Name + "%");
            return query.getResultList();
        } catch (RuntimeException E) {
            throw new DAException(E.getMessage());
        }
    }

    /**
     * Find book by unique id.
     *
     * @param id unique identification
     * @return finded book
     */
    @Override
    public Book findBookById(long id) {
        try {
            final Query query = entityManager.createQuery("SELECT m FROM Book as m WHERE m.idBook = :i");
            query.setParameter("i", id);
            return (Book) query.getSingleResult();
        } catch (RuntimeException E) {
            throw new DAException(E.getMessage());
        }
    }

    @Override
    public List<Book> findAllBooks() {
        try {
            final Query query = entityManager.createQuery("SELECT m FROM Book as m");
            return query.getResultList();
        } catch (RuntimeException E) {
            throw new DAException(E.getMessage());
        }
    }
}
