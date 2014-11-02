/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.datatransferobject;

/**
 *
 * @author Boris Valentovic - xvalent2
 */
public class LoanDTO {
    private int idLoan;

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
    
    
}
