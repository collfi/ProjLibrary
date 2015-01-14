package cz.fi.muni.pa165.entity;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Class defines member of library with basic registration information.
 *
 * @author Martin Malik <374128@mail.muni.cz>
 */
@Entity
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "ID_MEMBER")
    private long idMember;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "ADDRESS", nullable = false)
    private String address;

    @Column(name = "ISADMIN", nullable = false)
    private Boolean isAdmin;

    @OneToMany(mappedBy = "member")
    private Set<Loan> loans = new HashSet<Loan>();

    public Member() {
    }

    public Boolean getIsadmin() {
        return isAdmin;
    }

    public void setIsadmin(Boolean isadmin) {
        this.isAdmin = isadmin;
    }

    public Member(long idMember, String name, String email, String password, String address, Boolean isAdmin) {
        this.idMember = idMember;
        this.name = name;
        this.email = email;
        this.email = password;
        this.address = address;
        this.isAdmin = isAdmin;

        Log logger = LogFactory.getLog(getClass());
        logger.error(password);
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

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password; }

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
        hash = 13 * hash + Objects.hashCode(this.isAdmin);
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
        if (!Objects.equals(this.isAdmin, other.isAdmin)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("idMember: " + idMember + ", name: " + name + ", email: " + email + ", address: " + address
                + ", isadmin: " + isAdmin);
        return sb.toString();
    }
}