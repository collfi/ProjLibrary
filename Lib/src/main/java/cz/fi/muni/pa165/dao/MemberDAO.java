/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.dao.IGenericDAO;
import cz.fi.muni.pa165.entity.Book;
import cz.fi.muni.pa165.entity.Loan;
import cz.fi.muni.pa165.entity.Member;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

/**
 * Class for MemberDAO
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
    public List<Member> findMembersByName(String name) {
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
    public List<Member> findMembersByAddress(String address) {
        final Query query = entityManager.createQuery("SELECT mem FROM Member as mem WHERE mem.address like :address");
        query.setParameter("address","%" + address + "%");
        return query.getResultList();
    }
    
    @Override
    public List<Member> findMembersByBook(Book book) {
        final Query query = entityManager.createQuery(
                "SELECT pb.loan.member FROM PrintedBook AS pb WHERE pb.book.idBook = :idBook"
        );                  
        query.setParameter("idBook", book.getId());
        List<Member> members = query.getResultList();
        
        //po otestovani vymazat, vypis clenov
        for(Member mem : members){
            System.out.println(mem);
        }
        return members;
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
        Member mem = entityManager.merge(t);
        entityManager.remove(mem);
    }

    @Override
    public Member find(Member t) {
        final Query query = entityManager.createQuery("SELECT mem FROM Member as mem WHERE mem.idMember = :id");
        query.setParameter("id", t.getIdMember());
        return (Member) query.getSingleResult();
    }   
}