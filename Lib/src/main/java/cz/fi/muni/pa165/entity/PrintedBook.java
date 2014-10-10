/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

/**
 *
 * @author 
 */
@Entity
public class PrintedBook {
    
    public enum Condition {New, Used, Damaged}

    public PrintedBook() {
    }
    
    
    @Id
    @GeneratedValue
    private int idPrintedBook;
    
    @ManyToOne
    private Book book;
    
    @Column(nullable = true)
    private Boolean state;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Condition condition;
    
    @ManyToOne
    private Loan isInLoan;

    public PrintedBook(int idPrintedBook, Book book, Boolean state, Condition condition, Loan loan) {
        this.idPrintedBook = idPrintedBook;
        this.book = book;
        this.state = state;
        this.condition = condition;
        this.isInLoan = loan;
    }

    public int getIdPrintedBook() {
        return idPrintedBook;
    }

    public void setIdPrintedBook(int idPrintedBook) {
        this.idPrintedBook = idPrintedBook;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public Loan getIsInLoan() {
        return isInLoan;
    }

    public void setIsInLoan(Loan isInLoan) {
        this.isInLoan = isInLoan;
    }

    @Override
    public String toString() {
        return "PrintedBook{" + "idPrintedBook=" + idPrintedBook + ", book=" + book + ", state=" + state + ", condition=" + condition + ", isInLoan=" + isInLoan + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + this.idPrintedBook;
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
        final PrintedBook other = (PrintedBook) obj;
        if (this.idPrintedBook != other.idPrintedBook) {
            return false;
        }
        return true;
    }

  

   
    
    
    
}
