/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.data.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author michal.lukac, 430614
 */
@Entity
public class Book {
    
    public enum DepartmentEnum { Science, Hobby, Sport, Autobiografy, Religion}
    
    @Id
    @GeneratedValue
    int IdBook;
    
    @Column(nullable=false)
    String Name;
    
    Set<String> Authors = new HashSet<String>();
    
    String Description;
    
    @Column(nullable=false)
    String ISBN;
    
    Set<PrintedBook> Books = new HashSet<PrintedBook>();
    
    DepartmentEnum Department;
    
    
    public void setId(int id) {
        IdBook = id;
    }
    
    public int getId() {
        return IdBook;
    }
    
    public void setName(String name) {
        Name = name;
    }
    
    public String getName() {
        return Name;
    }
    
    public void setAuthors(Set<String> authors) {
        Authors = authors;
    }
    
    public Set<String> getAuthors() {
        return Authors;
    }
    
    public void setDescription(String description) {
        Description = description;
    }
    
    public String getDescription() {
        return Description;
    }
    
    public void setISBN(String isbn) {
        this.ISBN = isbn;
    }
    
    public String getISBN() {
        return ISBN;
    }
    
    public void setPrintedBooks(Set<PrintedBook> books) {
        this.Books = books;
    }
    
    public Set<PrintedBook> getPrintedBooks() {
        return this.Books;
    }
        
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + (int) (IdBook ^ (IdBook >>> 32));
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
	if (this.IdBook != other.IdBook)
            return false;
	return true;
    }

    @Override
    public String toString() {
	return "Book [id=" + IdBook + ", name=" + Name + ", description=" + Description + "]";
    }
}
