/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

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

    @OneToMany(mappedBy = "loan")
    private Set<PrintedBook> pbooks = new HashSet<PrintedBook>();

    @Column(nullable=false)
    private Date fromDate;

    @Column(nullable=false)
    private Date toDate;

    @Column(nullable=false)
    private boolean isReturned;

    private String description;

    @Column(nullable=false)
    private Date dateReturned;

    public Loan() {
    }

    /**
     *
     * @param idLoan id of loan
     * @param member member assigned to loan
     * @param pbooks borrowed books
     * @param fromDate date loan started from
     * @param toDate date loan ends
     * @param isReturned is loan closed
     * @param description description of the loan
     * @param dateReturned actual date of loan return
     */
    public Loan(int idLoan, Member member, Set<PrintedBook> pbooks, Date fromDate, Date toDate, boolean isReturned,
                String description, Date dateReturned) {
        this.idLoan = idLoan;
        this.member = member;
        this.pbooks = pbooks;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.isReturned = isReturned;
        this.description = description;
        this.dateReturned = dateReturned;
    }

    /**
     * Sets date of expected loan return
     * @param when date of expected return
     */
    public void setDateReturned(Date when) {
        this.dateReturned = when;
    }

    /**
     * sets description of the loan
     * @param description description of the loan
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * sets member assigned to the loan
     * @param member member assigned to loan
     */
    public void setMember(Member member) {
        this.member = member;
    }

    /**
     * sets date loan started from
     * @param fromDate date loan started from
     */
    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    /**
     * sets date loan ends
     * @param toDate    date loan ends
     */
    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    /**
     * sets state of loan
     * @param isReturned is loan closed
     */
    public void setReturned(boolean isReturned) {
        this.isReturned = isReturned;
    }

    /**
     * gets expected start date
     * @return expected loan start date
     */
    public Date getFromDate() {
        return fromDate;
    }

    /**
     * gets expected return date
     * @return expected return date
     */
    public Date getToDate() {
        return toDate;
    }

    /**
     * gets state of loan
     * @return state of loan(returned or not)
     */
    public boolean isReturned() {
        return isReturned;
    }

    /**
     * gets description of the loan
     * @return  description of the loan
     */
    public String getDescription() {
        return description;
    }

    /**
     * gets date of actual return
     * @return date of actual return
     */
    public Date getDateReturned() {
        return dateReturned;
    }

    /**
     * gets member assigned to the loan
     * @return  member assigned
     */
    public Member getMember() {

        return member;
    }

    /**
     * gets loan id
     * @return loan id
     */
    public int getIdLoan() {
        return idLoan;
    }

    /**
     * sets loan's id
     * @param idLoan    id of the loan
     */
    public void setIdLoan(int idLoan) {
        this.idLoan = idLoan;
    }

    /**
     * returns set of books borrowed with the loan
     * @return  set of books borrowed with the loan
     */
    public Set<PrintedBook> getPbooks() {
        return pbooks;
    }

    /**
     * sets books in loan
     * @param books books in loan
     */
    public void setPbooks(Set<PrintedBook> books) {
        this.pbooks = books;
    }

    @Override
    public String toString() {
        return this.getClass() + "{" + "idLoan=" + idLoan + ", fromDate=" + fromDate + ", toDate=" + toDate +
                ", dateReturned=" + dateReturned + ", isReturned=" + isReturned + ", Member=" + member
                + ", Description=" + description + ", PrintedBooks=" + pbooks + '}';
    }

    @Override
    public int hashCode() {
        return this.idLoan*3;
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
