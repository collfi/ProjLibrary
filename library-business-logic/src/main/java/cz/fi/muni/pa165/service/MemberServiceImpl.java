package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.dao.BookDAO;
import cz.fi.muni.pa165.dao.MemberDAO;
import cz.fi.muni.pa165.entity.Book;
import cz.fi.muni.pa165.entity.Member;
import cz.fi.muni.pa165.library.api.dto.BookDTO;
import cz.fi.muni.pa165.library.api.dto.MemberDTO;
import cz.fi.muni.pa165.library.api.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Martin Malik <374128@mail.muni.cz>
 */
@Transactional
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    MemberDAO memberDAO;

    @Autowired
    BookDAO bookDAO;

    @Override
    public void insertMember(MemberDTO memberDTO) {
        Member member = DTOEntityManager.memberDTOtoEntity(memberDTO);
        memberDAO.insert(member);
    }

    @Override
    public void updateMember(MemberDTO memberDTO) {
        Member member = DTOEntityManager.memberDTOtoEntity(memberDTO);
        memberDAO.update(member);
    }

    @Override
    public void deleteMember(MemberDTO memberDTO) {
        Member member = DTOEntityManager.memberDTOtoEntity(memberDTO);
        memberDAO.delete(member);
    }

    @Override
    public MemberDTO findMember(MemberDTO memberDTO) {
        Member member = memberDAO.find(DTOEntityManager.memberDTOtoEntity(memberDTO));
        return DTOEntityManager.memberEntitytoDTO(member);
    }

    @Override
    public List<MemberDTO> findAllMembers() {
        List<Member> members = memberDAO.findAllMembers();
        List<MemberDTO> membersDTO = new ArrayList<MemberDTO>();
        for (Member member : members) {
            membersDTO.add(DTOEntityManager.memberEntitytoDTO(member));
        }

        return membersDTO;
    }

    @Override
    public MemberDTO findMemberByIdMember(long id) {
        return DTOEntityManager.memberEntitytoDTO(memberDAO.findMemberByIdMember(id));
    }

    @Override
    public List<MemberDTO> findMembersByName(String name) {
        List<Member> members = memberDAO.findMembersByName(name);
        return MemberEntityArrayToMemberDTOArray(members);
    }

    @Override
    public MemberDTO findMemberByEmail(String email) {
        return DTOEntityManager.memberEntitytoDTO(memberDAO.findMemberByEmail(email));
    }

    @Override
    public List<MemberDTO> findMembersByAddress(String address) {
        List<Member> members = memberDAO.findMembersByAddress(address);
        return MemberEntityArrayToMemberDTOArray(members);
    }

    @Override
    public List<MemberDTO> findMembersByBook(BookDTO bookDTO) {
        Book book = bookDAO.find(DTOEntityManager.bookDTOtoEntity(bookDTO));
        List<Member> members = memberDAO.findMembersByBook(book);
        return MemberEntityArrayToMemberDTOArray(members);
    }

    private List<MemberDTO> MemberEntityArrayToMemberDTOArray(List<Member> members) {
        List<MemberDTO> membersDTO = new ArrayList<MemberDTO>();
        for (Member member : members) {
            membersDTO.add(DTOEntityManager.memberEntitytoDTO(member));
        }

        return membersDTO;
    }

    public MemberDAO getMemberDAO() {
        return memberDAO;
    }

    public void setMemberDAO(MemberDAO memberDAO) {
        this.memberDAO = memberDAO;
    }

    public BookDAO getBookDAO() {
        return bookDAO;
    }

    public void setBookDAO(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }
}