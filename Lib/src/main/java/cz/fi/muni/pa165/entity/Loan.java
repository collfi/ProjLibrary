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
 * @author sergii
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
        return this.idLoan;
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
