/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.datatransferobject;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

 /**
 * @author Sergii Pylypenko, 430519
 */
public class LoanDTO {
    private int idLoan;

    private MemberDTO member;

    private PrintedBookDTO printedBook;

    private Date fromDate;

    private Date toDate;

    private boolean returned;

    @Override
    public String toString() {
        return "LoanDTO{" +
                "idLoan=" + idLoan +
                ", member=" + member +
                ", pbooks=" + printedBook +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                ", isReturned=" + returned +
                ", description='" + description + '\'' +
                ", dateReturned=" + dateReturned +
                '}';
    }

    private String description;

    public LoanDTO() {

    }

    public LoanDTO(Date dateReturned, String description, boolean isReturned, Date toDate, Date fromDate,
                   PrintedBookDTO printedBook, MemberDTO member, int idLoan) {
        this.dateReturned = dateReturned;
        this.description = description;
        this.returned = isReturned;
        this.toDate = toDate;
        this.fromDate = fromDate;
        this.printedBook = printedBook;
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


    public MemberDTO getMember() {
        return member;
    }

    public void setMember(MemberDTO member) {
        this.member = member;
    }

    public PrintedBookDTO getPrintedBook() {
        return printedBook;
    }

    public void setPrintedBook(PrintedBookDTO printedBook) {
        this.printedBook = printedBook;
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

    public void setDateReturned(Date dateReturned) {
        this.dateReturned = dateReturned;
    }
}
