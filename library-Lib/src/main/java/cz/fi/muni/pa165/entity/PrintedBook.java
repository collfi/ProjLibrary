/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.entity;

import javax.persistence.*;

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
    private long idPrintedBook;
    
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
     * Constructor for printed book.
     * 
     * @param idPrintedBook id of printed book
     * @param book which book this printed book belongs
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

    public long getIdPrintedBook() {
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

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        if (loan == null) {
            this.loan = null;
            this.setState(Boolean.FALSE);
        }
        else {
            this.loan = loan;
            this.setState(Boolean.TRUE);
        }
    }

    @Override
    public String toString() {
        return "PrintedBook{" + "idPrintedBook=" + idPrintedBook + ", book=" + book + ", state=" + state + ", condition=" + condition + ", isInLoan=" + loan + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + (int) this.idPrintedBook;
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
