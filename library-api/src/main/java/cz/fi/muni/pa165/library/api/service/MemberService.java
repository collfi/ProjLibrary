package cz.fi.muni.pa165.library.api.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import java.util.List;

import cz.fi.muni.pa165.library.api.dto.BookDTO;
import cz.fi.muni.pa165.library.api.dto.MemberDTO;

/**
 * Interface for MemberService.
 *
 * @author Martin Malik <374128@mail.muni.cz>
 */
public interface MemberService extends UserDetailsService {

    public void insertMember(MemberDTO memberDTO);

    public void updateMember(MemberDTO memberDTO);

    public void deleteMember(MemberDTO memberDTO);

    public List<MemberDTO> findAllMembers();

    public MemberDTO findMember(MemberDTO memberDTO);

    public MemberDTO findMemberByIdMember(long id);

    public List<MemberDTO> findMembersByName(String name);

    public List<MemberDTO> findMembersByEmail(String email);

    public List<MemberDTO> findMembersByAddress(String address);

    public List<MemberDTO> findMembersByBook(BookDTO bookDTO);

}
