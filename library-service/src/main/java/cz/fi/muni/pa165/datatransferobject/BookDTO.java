/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.datatransferobject;

import cz.fi.muni.pa165.entity.Book.Department;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author michal.lukac - xlukac, 430614
 */
public class BookDTO {

    private long idBook;
    @NotEmpty
    private String name;
    @NotEmpty
    private String authors;
    @NotEmpty
    private String description;
    @NotEmpty
    private String ISBN;

    private Set<PrintedBookDTO> Books = new HashSet<PrintedBookDTO>();

    private Department department;


    public long getIdBook() {
        return idBook;
    }

    public void setIdBook(long idBook) {
        this.idBook = idBook;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public Set<PrintedBookDTO> getBooks() {
        return Books;
    }

    public void setBooks(Set<PrintedBookDTO> Books) {
        this.Books = Books;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + (int) (this.idBook ^ (this.idBook >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BookDTO other = (BookDTO) obj;
        if (this.idBook != other.idBook) {
            return false;
        }
        return true;
    }
}
