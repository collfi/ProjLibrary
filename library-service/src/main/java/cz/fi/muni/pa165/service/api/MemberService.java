package cz.fi.muni.pa165.service.api;

import cz.fi.muni.pa165.datatransferobject.BookDTO;
import cz.fi.muni.pa165.datatransferobject.MemberDTO;
import java.util.List;

/**
 *
 * @author Martin Malik <374128@mail.muni.cz>
 */
public interface MemberService {
    
    public void insertMember(MemberDTO memberDTO);
    
    public void updateMember(MemberDTO memberDTO);
    
    public void deleteMember(MemberDTO memberDTO);
    
    public MemberDTO find(MemberDTO memberDTO);
    
    public MemberDTO findMemberByIdMember(long id); 
    
    public List<MemberDTO> findMembersByName(String name);
    
    public MemberDTO findMemberByEmail(String email);
    
    public List<MemberDTO> findMembersByAddress(String address);
    
    public List<MemberDTO> findMembersByBook(BookDTO bookDTO);
    
}
