/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.data.services;

import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import pa165.data.dao.IBookDAO;
import pa165.data.dao.IGenericDAO;
import pa165.data.entity.Book;

/**
 *
 * @author michal.lukac, 430614
 */
public class BookDAO implements IBookDAO, IGenericDAO<Book>{

    @PersistenceContext(unitName = "book-unit", type = PersistenceContextType.EXTENDED)
    private EntityManager entityManager;
    
    @Override
    public void Insert(Book t) {
        entityManager.persist(t);
    }

    @Override
    public void Update(Book t) {
        entityManager.persist(t);
    }

    @Override
    public void Delete(Book t) {
        entityManager.remove(t);
    }

    @Override
    public Book Find(Book t) {
        final Query query = entityManager.createQuery("SELECT m FROM Book as m WHERE m.IdBook = :book");
        query.setParameter("book", t.getId());
        return (Book) query.getSingleResult();
    }

    @Override
    public List<Book> FindBooksByISBN(String Isbn) {
        final Query query = entityManager.createQuery("SELECT m FROM Book as m WHERE m.ISBN = :book");
        query.setParameter("book", Isbn);
        return query.getResultList();
    }

    @Override
    public List<Book> FindBooksByAuthor(String Author) {
        final Query query = entityManager.createQuery("SELECT m FROM Book as m WHERE m.Author = :book");
        query.setParameter("book", Author);
        return query.getResultList();
    }

    @Override
    public List<Book> FindBooksByDepartment(Book.DepartmentEnum en) {
        final Query query = entityManager.createQuery("SELECT m FROM Book as m WHERE m.Department = :book");
        query.setParameter("book", en);
        return query.getResultList();
    }

    @Override
    public List<Book> FindBooksByName(String Name) {
        final Query query = entityManager.createQuery("SELECT m FROM Book as m WHERE m.Name = :book");
        query.setParameter("book", Name);
        return query.getResultList();
    }
}
