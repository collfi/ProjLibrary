package cz.fi.muni.pa165.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by sergii
 */
@Entity
public class Member {
    @Id
    @GeneratedValue
    private int idMember;

    @Column(nullable = false)
    private String name;

    private String email;

    private String address;

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

    @OneToMany
    private Set<Loan> loans = new HashSet<Loan>();

    public int getIdMember() {
        return idMember;
    }

    public void setIdMember(int idMember) {
        this.idMember = idMember;
    }

    public Set<Loan> getLoans() {
        return this.loans;
    }

    public void setLoans(Set<Loan> loans) {
        this.loans = loans;
    }
}
