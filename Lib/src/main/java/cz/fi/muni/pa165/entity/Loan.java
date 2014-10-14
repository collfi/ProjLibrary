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
    @Temporal(TemporalType.DATE)
    private Date fromDate;

    @Temporal(TemporalType.DATE)
    @Column(nullable=false)
    private Date toDate;

    @Column(nullable=false)
    private boolean isReturned;

    private String description;

    @Column(nullable=false)
    @Temporal(TemporalType.DATE)
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

    public void setDateReturned(Date when) {
        this.dateReturned = when;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public void setReturned(boolean isReturned) {
        this.isReturned = isReturned;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public boolean isReturned() {
        return isReturned;
    }

    public String getDescription() {
        return description;
    }

    public Date getDateReturned() {
        return dateReturned;
    }

    public Member getMember() {

        return member;
    }

    public int getIdLoan() {
        return idLoan;
    }

    public void setIdLoan(int idLoan) {
        this.idLoan = idLoan;
    }

    public Set<PrintedBook> getPbooks() {
        return pbooks;
    }

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
