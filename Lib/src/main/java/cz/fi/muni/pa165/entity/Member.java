package cz.fi.muni.pa165.entity;

import cz.fi.muni.pa165.entity.Loan;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Class defines member of library with basic registration information. 
 *  
 * @author Martin Malik <374128@mail.muni.cz>
 */
@Entity
public class Member {
    
    @Id
    @GeneratedValue
    @Column(name="ID_MEMBER")
    private long idMember;
    
    @Column(name="NAME", nullable=false)
    private String name;
    
    @Column(name="EMAIL", nullable=false)
    private String email;
    
    @Column(name="ADDRESS", nullable=false)
    private String address;
    
    @OneToMany(mappedBy = "member")
    private Set<Loan> loans = new HashSet<Loan>();
    
    public Member(){    
    }

    public Member(long idMember, String name, String email, String address) {
        this.idMember = idMember;
        this.name = name;
        this.email = email;
        this.address = address;
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

    public Set<Loan> getLoans() {
        return loans;
    }

    public void setLoans(Set<Loan> loans) {
        this.loans = loans;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + (int) (this.idMember ^ (this.idMember >>> 32));
        hash = 13 * hash + Objects.hashCode(this.name);
        hash = 13 * hash + Objects.hashCode(this.email);
        hash = 13 * hash + Objects.hashCode(this.address);
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
        final Member other = (Member) obj;
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
            
            for(Loan loan : loans){
                sb.append(loan.toString());
            }
            
            sb.append("]");
            
            return sb.toString();
        }
    }
}