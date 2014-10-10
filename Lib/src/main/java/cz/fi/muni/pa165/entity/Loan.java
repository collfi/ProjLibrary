/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Boris Valentovic - xvalent2
 */
@Entity
public class Loan {


    @Id
    @GeneratedValue
    private int idLoan;
    
    @OneToMany(mappedBy="isInLoan")
    private Set<PrintedBook> books = new HashSet<PrintedBook>();

    public int getIdLoan() {
        return idLoan;
    }

    public void setIdLoan(int idLoan) {
        this.idLoan = idLoan;
    }

    public Set<PrintedBook> getBooks() {
        return books;
    }

    public void setBooks(Set<PrintedBook> books) {
        this.books = books;
    }
    
   
    
}
