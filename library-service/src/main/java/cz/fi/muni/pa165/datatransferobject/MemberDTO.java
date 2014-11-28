/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.datatransferobject;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
/**
 *
 * @author Martin Malik <374128@mail.muni.cz>
 */
public class MemberDTO {
    
    private long idMember;
    
    @NotEmpty
    private String name;
    
    @NotEmpty
    @Email
    private String email;
    
    @NotEmpty
    private String address;
    
    private Set<LoanDTO> loans = new HashSet<LoanDTO>();
    
    public MemberDTO(){    
    }

    public long getIdMember() {
        return idMember;
    }

    public void setIdMember(long idMember) {
        this.idMember = idMember;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<LoanDTO> getLoans() {
        return loans;
    }

    public void setLoans(Set<LoanDTO> loans) {
        this.loans = loans;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + (int) (this.idMember ^ (this.idMember >>> 32));
        hash = 79 * hash + Objects.hashCode(this.name);
        hash = 79 * hash + Objects.hashCode(this.email);
        hash = 79 * hash + Objects.hashCode(this.address);
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
        final MemberDTO other = (MemberDTO) obj;
        if (this.idMember != other.idMember) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.address, other.address)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("idMember: " + idMember + ", name: " + name + ", email: " + email + ", address: " + address);
        
        if(loans == null){
            return sb.toString();
        }else{
            sb.append(" Loans = [ ");
            
            for(LoanDTO loan : loans){
                sb.append(loan.toString());
            }
            
            sb.append("]");
            
            return sb.toString();
        }
    }
}