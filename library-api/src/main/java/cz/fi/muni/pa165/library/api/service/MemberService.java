package cz.fi.muni.pa165.library.api.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
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

//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void insertMember(MemberDTO memberDTO);

//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void updateMember(MemberDTO memberDTO);

//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteMember(MemberDTO memberDTO);

//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<MemberDTO> findAllMembers();

//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public MemberDTO findMember(MemberDTO memberDTO);

//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public MemberDTO findMemberByIdMember(long id);

//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<MemberDTO> findMembersByName(String name);

//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<MemberDTO> findMembersByEmail(String email);

//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<MemberDTO> findMembersByAddress(String address);

//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<MemberDTO> findMembersByBook(BookDTO bookDTO);

    @Override
    UserDetails loadUserByUsername(String s);

}
