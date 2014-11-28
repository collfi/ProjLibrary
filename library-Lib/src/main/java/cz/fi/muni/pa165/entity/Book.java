/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

/**
 *
 * @author Michal Lukac, 430614, xlukac
 */
@Entity
public class Book {
    public enum Department { Science, Sport, Autobiografy, Religion}
    
    @Id
    @GeneratedValue
    private long idBook;
    
    @Column(nullable=false, unique = true)
    private String name;
    
    private String authors;
    
    private String description;
    
    @Column(nullable=false, unique = true)
    private String ISBN;
    
    @OneToMany(mappedBy="book")
    private Set<PrintedBook> Books = new HashSet<PrintedBook>();
    
    @Enumerated(EnumType.STRING)
    private Department department;

    public Book() {
    }

    /**
     * The constructor 2.
     * @param IdBook id of book
     * @param Name name of book
     * @param authors string represents authors
     * @param Description description of book
     * @param Isbn isbn of book
     * @param Books set of printed books
     * @param Department the department which book belongs
     */
    public Book(int IdBook, String Name, String authors, String Description,
            String Isbn, Set<PrintedBook> Books, Department Department) {
        this.idBook = IdBook;
        this.name = Name;
        this.authors = authors;
        this.description = Description;
        this.ISBN = Isbn;
        this.Books = Books;
        this.department = Department;
    }
    
    public void setIdBook(long IdBook) {
        this.idBook = IdBook;
    }

    public long getIdBook() {
        return this.idBook;
    }

    public void setName(String Name) {
        this.name = Name;
    }

    public String getName() {
        return this.name;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public void setDescription(String Description) {
        this.description = Description;
    }

    public String getDescription() {
        return this.description;
    }

    public void setISBN(String Isbn) {
        this.ISBN = Isbn;
    }

    public String getISBN() {
        return this.ISBN;
    }

    public void setPrintedBooks(Set<PrintedBook> Books) {
        this.Books = Books;
    }

    public Set<PrintedBook> getPrintedBooks() {
        return this.Books;
    }

    public Department getDepartment() {
        return this.department;
    }

    public void setDepartment(Department Department) {
        this.department = Department;
    }
    
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + (int) (idBook ^ (idBook >>> 32));
	return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
	if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
            Book other = (Book) obj;
	if (this.idBook != other.idBook)
            return false;
	return true;
    }

    @Override
    public String toString() {
	return "Book [id=" + idBook + ", name=" + name + ", description=" + description + "]";
    }
}
