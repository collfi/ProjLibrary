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

/**
 *
 * @author @author Boris Valentovic - xvalent2
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
    private Loan loan;

    /**
     * Constructor for printedbook.
     * 
     * @param idPrintedBook id of printed book
     * @param book which book this printedbook belongs
     * @param state state if is borrowed or not
     * @param condition is it new or used, ...
     * @param loan in which loan it participates
     */
    public PrintedBook(int idPrintedBook, Book book, Boolean state, Condition condition, Loan loan) {
        this.idPrintedBook = idPrintedBook;
        this.book = book;
        this.state = state;
        this.condition = condition;
        this.loan = loan;
    }

    /**
     * Returns id
     * @return id
     */
    public int getIdPrintedBook() {
        return idPrintedBook;
    }

    /**
     * Sets id
     * @param idPrintedBook id
     */
    public void setIdPrintedBook(int idPrintedBook) {
        this.idPrintedBook = idPrintedBook;
    }

    /**
     * Returns book
     * @return the book which printedbook belongs
     */
    public Book getBook() {
        return book;
    }

    /**
     * Sets book
     * @param book the book which this book belongs 
     */
    public void setBook(Book book) {
        this.book = book;
    }

    /**
     * Returns state
     * @return state for the book 
     */
    public Boolean getState() {
        return state;
    }

    /**
     * Sets state
     * @param state is it borrowed or not
     */
    public void setState(Boolean state) {
        this.state = state;
    }

    /**
     * Returns condition.
     * @return return condition.
     */
    public Condition getCondition() {
        return condition;
    }

    /**
     * Set conditions.
     * @param condition if is new, used, .. 
     */
    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    /**
     * Returns loan
     * @return loan in which book is participated  
     */
    public Loan getLoan() {
        return loan;
    }

    /**
     * Sets loan.
     * @param loan in which book is participated
     */
    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    @Override
    public String toString() {
        return "PrintedBook{" + "idPrintedBook=" + idPrintedBook + ", book=" + book + ", state=" + state + ", condition=" + condition + ", isInLoan=" + loan + '}';
    }

    @Override
    public int hashCode() {
        //TODO why isn't just idPrintedBook?
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
        return this.idPrintedBook == other.idPrintedBook;
    }
}
