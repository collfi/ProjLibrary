/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.dao.IGenericDAO;
import cz.fi.muni.pa165.entity.Member;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

/**
 *
 * @author Martin Malik <374128@mail.muni.cz>
 */
public class MemberDAO implements IMemberDAO, IGenericDAO<Member>{

    @PersistenceContext(unitName = "member-unit", type = PersistenceContextType.EXTENDED)
    private EntityManager entityManager;

    @Override
    public Member findMemberByIdMember(long id) {
        final Query query = entityManager.createQuery("SELECT mem FROM Member as mem WHERE mem.idMember = :id");
        query.setParameter("id", id);
        return (Member) query.getSingleResult();
    }

    @Override
    public List<Member> findMemberByName(String name) {
        final Query query = entityManager.createQuery("SELECT mem FROM Member as mem WHERE mem.name like :name");
        query.setParameter("name","%" + name + "%");
        return query.getResultList();
    }

    @Override
    public Member findMemberByEmail(String email) {
        final Query query = entityManager.createQuery("SELECT mem FROM Member AS mem WHERE mem.email = :email");
        query.setParameter("email", email);
        return (Member) query.getSingleResult();
    }
    
    @Override
    public List<Member> findMemberByAddress(String adress) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void setManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void insert(Member t) {
        entityManager.persist(t);
    }

    @Override
    public void update(Member t) {
        final Query query = entityManager.createQuery("UPDATE Member SET name = :name," +
                                "email = :email, adress = :address WHERE idMember = :id");
        query.setParameter("id", t.getIdMember());
        query.setParameter("name", t.getName());
        query.setParameter("email", t.getEmail());
        query.setParameter("address", t.getAddress());
    }

    @Override
    public void delete(Member t) {
        entityManager.remove(t);
    }

    @Override
    public Member find(Member t) {
        final Query query = entityManager.createQuery("SELECT mem FROM Member as mem WHERE mem.idMember = :id");
        query.setParameter("id", t.getIdMember());
        return (Member) query.getSingleResult();
    }   
}