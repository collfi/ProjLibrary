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
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import javax.persistence.OneToMany;

/**
 * Entity class represents Book.
 * @author michal.lukac, 430614
 */
@Entity
public class Book {
    
    public enum DepartmentEnum { Science, Hobby, Sport, Autobiografy, Religion}
    
    @Id
    @GeneratedValue
    private int IdBook;
    
    @Column(nullable=false)
    private String Name;
    
    @OneToMany(mappedBy="cage")
    private Set<String> Authors = new HashSet<String>();
    
    private String Description;
    
    @Column(nullable=false)
    private String ISBN;
    
    private Set<PrintedBook> Books = new HashSet<PrintedBook>();
    
    @Enumerated(EnumType.STRING)
    private DepartmentEnum Department;
    
    public Book() {
    }

    public Book(int IdBook, String Name, Set<String> Authors, String Description,
            String Isbn, Set<PrintedBook> Books, DepartmentEnum Department) {
        this.IdBook = IdBook;
        this.Name = Name;
        this.Authors = Authors;
        this.Description = Description;
        this.ISBN = Isbn;
        this.Books = Books;
        this.Department = Department;
    }

    
    public void setId(int IdBook) {
        this.IdBook = IdBook;
    }
    
    public int getId() {
        return this.IdBook;
    }
    
    public void setName(String Name) {
        this.Name = Name;
    }
    
    public String getName() {
        return this.Name;
    }
    
    public void setAuthors(Set<String> Authors) {
        this.Authors = Authors;
    }
    
    public Set<String> getAuthors() {
        return this.Authors;
    }
    
    public void setDescription(String Description) {
        this.Description = Description;
    }
    
    public String getDescription() {
        return this.Description;
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
    
    public DepartmentEnum getDepartment() {
        return this.Department;
    }
       
    public void setDapertment(DepartmentEnum Department) {
        this.Department = Department;
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
