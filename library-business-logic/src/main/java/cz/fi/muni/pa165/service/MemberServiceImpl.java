package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.dao.BookDAO;
import cz.fi.muni.pa165.dao.MemberDAO;
import cz.fi.muni.pa165.entity.Book;
import cz.fi.muni.pa165.entity.Member;
import cz.fi.muni.pa165.library.api.dto.BookDTO;
import cz.fi.muni.pa165.library.api.dto.MemberDTO;
import cz.fi.muni.pa165.library.api.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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

    protected final Log logger = LogFactory.getLog(getClass());

    @Override
    public UserDetails loadUserByUsername(final String username)
            throws UsernameNotFoundException {

        logger.error("loadUserByUsername username="+username);

        logger.error("tut before throwing");
        if(!username.equals("rest")) {
            logger.error("tut throwing");
            throw new UsernameNotFoundException(username + " not found");
        }

        //creating dummy user details, should do JDBC operations
        return new UserDetails() {

            private static final long serialVersionUID = 2059202961588104658L;

            @Override
            public boolean isEnabled() {
                return true;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return true;
            }

            @Override
            public boolean isAccountNonLocked() {
                return true;
            }

            @Override
            public boolean isAccountNonExpired() {
                return true;
            }

            @Override
            public String getUsername() {
                logger.info("getUsername username="+username);
                return username;
            }

            @Override
            public String getPassword() {
                logger.error("tut getting password");
                return "rest";
            }

            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                logger.error("tut authority");
                List<SimpleGrantedAuthority> auths = new java.util.ArrayList<SimpleGrantedAuthority>();
                auths.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
                return auths;
            }
        };
    }

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
    public List<MemberDTO> findMembersByEmail(String email) {
        List<Member> members = memberDAO.findMembersByEmail(email);
        return MemberEntityArrayToMemberDTOArray(members);
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

    public void setMemberDAO(MemberDAO memberDAO) {
        this.memberDAO = memberDAO;
    }

    public void setBookDAO(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }
}