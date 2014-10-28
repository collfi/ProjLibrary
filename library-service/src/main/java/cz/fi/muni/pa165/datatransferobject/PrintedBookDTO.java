/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.datatransferobject;

import cz.fi.muni.pa165.entity.Book;
import cz.fi.muni.pa165.entity.Loan;
import cz.fi.muni.pa165.entity.PrintedBook.Condition;

/**
 *
 * @author Boris Valentovic - xvalent2
 */
public class PrintedBookDTO {
    private Long id;
    
    private Book book;
    
    private Boolean state;
    
    private Condition condition;
    
    private Loan loan;
    
    public PrintedBookDTO () {
        
    }

    public PrintedBookDTO(Long id, Book book, Boolean state, Condition condition, Loan loan) {
        this.id = id;
        this.book = book;
        this.state = state;
        this.condition = condition;
        this.loan = loan;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        this.loan = loan;
    }
    
    
    
    
}
