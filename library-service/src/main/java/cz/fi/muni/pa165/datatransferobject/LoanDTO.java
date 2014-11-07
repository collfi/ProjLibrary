/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.datatransferobject;

import cz.fi.muni.pa165.entity.Member;
import cz.fi.muni.pa165.entity.PrintedBook;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

 /**
 * @author Sergii Pylypenko, 430519
 */
public class LoanDTO {
    private int idLoan;

    private Member member;

    private Set<PrintedBook> pbooks = new HashSet<>();

    private Date fromDate;

    private Date toDate;

    private boolean isReturned;

    @Override
    public String toString() {
        return "LoanDTO{" +
                "idLoan=" + idLoan +
                ", member=" + member +
                ", pbooks=" + pbooks +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                ", isReturned=" + isReturned +
                ", description='" + description + '\'' +
                ", dateReturned=" + dateReturned +
                '}';
    }

    private String description;

    public LoanDTO() {

    }

    public LoanDTO(Date dateReturned, String description, boolean isReturned, Date toDate, Date fromDate,
                   Set<PrintedBook> pbooks, Member member, int idLoan) {
        this.dateReturned = dateReturned;
        this.description = description;
        this.isReturned = isReturned;
        this.toDate = toDate;
        this.fromDate = fromDate;
        this.pbooks = pbooks;
        this.member = member;
        this.idLoan = idLoan;
    }

    private Date dateReturned;

    public int getIdLoan() {
        return idLoan;
    }

    public void setIdLoan(int idLoan) {
        this.idLoan = idLoan;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + this.idLoan;
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
        final LoanDTO other = (LoanDTO) obj;
        if (this.idLoan != other.idLoan) {
            return false;
        }
        return true;
    }


    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Set<PrintedBook> getPbooks() {
        return pbooks;
    }

    public void setPbooks(Set<PrintedBook> pbooks) {
        this.pbooks = pbooks;
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

    public boolean isReturned() {
        return isReturned;
    }

    public void setReturned(boolean isReturned) {
        this.isReturned = isReturned;
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

    public void setDateReturned(Date dateReturned) {
        this.dateReturned = dateReturned;
    }
}
