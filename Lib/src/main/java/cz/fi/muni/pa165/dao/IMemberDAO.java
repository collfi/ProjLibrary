/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.entity.Book;
import cz.fi.muni.pa165.entity.Member;
import java.util.List;

/**
 *
 * @author Martin Malik <374128@mail.muni.cz>
 */
public interface IMemberDAO {
 
    public Member findMemberByIdMember(long id); 
    
    public List<Member> findMembersByName(String name);
    
    public Member findMemberByEmail(String email);
    
    public List<Member> findMembersByAddress(String address);
    
    public List<Member> findMembersByBook(Book book);
}
