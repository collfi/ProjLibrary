package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.entity.Book;
import cz.fi.muni.pa165.entity.Member;

import java.util.List;

/**
 * Class for interface of MemberDAO
 *
 * @author Martin Malik <374128@mail.muni.cz>
 */
public interface MemberDAO extends GenericDAO<Member> {

    /**
     * Method finds all members.
     *
     * @return members
     */
    public List<Member> findAllMembers();

    /**
     * Method finds member by unique identifier.
     *
     * @param id identifier
     * @return member
     */
    public Member findMemberByIdMember(long id);

    /**
     * Method finds members by name.
     * <p/>
     * E.g. John Brown, Jesse John, Lee Brown
     * Method returns John Brown and Jesse John wih parameter value John.
     *
     * @param name name of member
     * @return list of members
     */
    public List<Member> findMembersByName(String name);

    /**
     * Method returns member by email. Every member has unique email.
     *
     * @param email email of member
     * @return member
     */
    public List<Member> findMembersByEmail(String email);

    /**
     * Method returns list of members by address.
     * E.g. John Brown - Brno 156/2, Jesse John - Tererova 123/5 Brno
     * Method returns John Brown and Jesse John wih parameter value Brno.
     *
     * @param address address of member
     * @return list of members
     */
    public List<Member> findMembersByAddress(String address);

    /**
     * Method returns list of members, that they have borrowed printed books.
     *
     * @param book book
     * @return list of members
     */
    public List<Member> findMembersByBook(Book book);
}