/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Pylypenko Sergii <430519@mail.muni.cz>
 */
@Entity
public class Loan {

    @Id
    @GeneratedValue
    private int idLoan;

    @ManyToOne
    private Member member;

    @OneToOne
    private PrintedBook printedBook;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fromDate;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date toDate;

    @Column(nullable = false)
    private boolean returned;

    private String description;

    @Column()
    @Temporal(TemporalType.DATE)
    private Date dateReturned;

    public Loan() {
    }

    /**
     * @param idLoan       id of loan
     * @param member       member assigned to loan
     * @param printedBook  borrowed book
     * @param fromDate     date loan started from
     * @param toDate       date loan ends
     * @param isReturned   is loan closed
     * @param description  description of the loan
     * @param dateReturned actual date of loan return
     */
    public Loan(int idLoan, Member member, PrintedBook printedBook, Date fromDate, Date toDate, boolean isReturned,
                String description, Date dateReturned) {
        this.idLoan = idLoan;
        this.member = member;
        this.printedBook = printedBook;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.returned = isReturned;
        this.description = description;
        this.dateReturned = dateReturned;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public boolean getReturned() {
        return returned;
    }

    public void setReturned(boolean isReturned) {
        this.returned = isReturned;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateReturned() {
        return dateReturned;
    }

    public void setDateReturned(Date when) {
        this.dateReturned = when;
    }

    public Member getMember() {

        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public int getIdLoan() {
        return idLoan;
    }

    public void setIdLoan(int idLoan) {
        this.idLoan = idLoan;
    }

    public PrintedBook getPrintedBook() {
        return this.printedBook;
    }

    public void setPrintedBook(PrintedBook printedBook) {
        this.printedBook = printedBook;
    }

    @Override
    public String toString() {
        return this.getClass() + "{" + "idLoan=" + idLoan + ", fromDate=" + fromDate.toString() + ", toDate=" + toDate.toString() +
                ", dateReturned=" + dateReturned + ", isReturned=" + returned + ", Member=" + member
                + ", Description=" + description + ", PrintedBooks=" + printedBook.toString() + '}';
    }

    @Override
    public int hashCode() {
        return this.idLoan * 3;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Loan other = (Loan) obj;
        return this.idLoan == other.idLoan;
    }


}
