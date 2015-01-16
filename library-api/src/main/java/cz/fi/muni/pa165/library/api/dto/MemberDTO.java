/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.library.api.dto;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

/**
 * Data Transfer Object of Member
 *
 * @author Martin Malik <374128@mail.muni.cz>
 */

public class MemberDTO implements UserDetails {

    private long idMember;

    @NotEmpty
    private String name;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    private String address;

    @NotEmpty
    private String password;

    private Boolean isAdmin = false;

    private Set<LoanDTO> loans = new HashSet<LoanDTO>();

    public MemberDTO() {
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public MemberDTO(long idMember, String name, String email, String address, String password, Boolean isAdmin,
                     Set<LoanDTO> loans) {
        this.idMember = idMember;
        this.name = name;
        this.email = email;
        this.address = address;
        this.password = password;
        this.isAdmin = isAdmin;

        this.loans = loans;
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
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + (int) (this.idMember ^ (this.idMember >>> 32));
        hash = 79 * hash + Objects.hashCode(this.name);
        hash = 79 * hash + Objects.hashCode(this.email);
        hash = 79 * hash + Objects.hashCode(this.address);
        hash = 79 * hash + Objects.hashCode(this.password);
        hash = 79 * hash + Objects.hashCode(this.isAdmin);
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
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.isAdmin, other.isAdmin)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("idMember: " + idMember + ", name: " + name + ", email: " + email + ", address: " + address
                + ", password: " + password + ", isAdmin: " + isAdmin);
        if (loans == null) {
            return sb.toString();
        } else {
            sb.append(" Loans = [ ");
            for (LoanDTO loan : loans) {
                sb.append(loan.toString());
            }
            sb.append("]");
            return sb.toString();
        }
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @com.fasterxml.jackson.annotation.JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> auths = new java.util.ArrayList<SimpleGrantedAuthority>();
        if (this.isAdmin) {
            auths.add(new SimpleGrantedAuthority("ROLE_USER"));
            auths.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        else {
            auths.add(new SimpleGrantedAuthority("ROLE_USER"));
        }
        return auths;
    }
}