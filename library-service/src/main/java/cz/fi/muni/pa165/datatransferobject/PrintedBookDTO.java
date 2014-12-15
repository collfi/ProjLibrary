/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.datatransferobject;

import cz.fi.muni.pa165.entity.PrintedBook.Condition;

import java.io.Serializable;
import java.util.Objects;

/**
 * Data Transfer Object of Printed Book
 *
 * @author Boris Valentovic - xvalent2
 */
public class PrintedBookDTO implements Serializable {
    private Long idPrintedBook;

    private BookDTO book;

    private Boolean state;

    private Condition condition;

    private LoanDTO loan;

    public PrintedBookDTO() {

    }

    public PrintedBookDTO(Long id, BookDTO book, Boolean state, Condition condition, LoanDTO loan) {
        this.idPrintedBook = id;
        this.book = book;
        this.state = state;
        this.condition = condition;
        this.loan = loan;
    }

    public PrintedBookDTO(BookDTO book, Boolean state, Condition condition, LoanDTO loan) {
        this.book = book;
        this.state = state;
        this.condition = condition;
        this.loan = loan;
    }

    public Long getIdPrintedBook() {
        return idPrintedBook;
    }

    public void setIdPrintedBook(Long idPrintedBook) {
        this.idPrintedBook = idPrintedBook;
    }

    public BookDTO getBook() {
        return book;
    }

    public void setBook(BookDTO book) {
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

    public LoanDTO getLoan() {
        return loan;
    }

    public void setLoan(LoanDTO loan) {
        this.loan = loan;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.idPrintedBook);
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
        final PrintedBookDTO other = (PrintedBookDTO) obj;
        if (!Objects.equals(this.idPrintedBook, other.idPrintedBook)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PrintedBookDTO{" + "idPrintedBook=" + idPrintedBook + ", book=" + book + ", state=" + state + ", condition=" + condition + ", loan=" + loan + '}';
    }


}
