package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.dao.BookDAO;
import cz.fi.muni.pa165.dao.MemberDAO;
import cz.fi.muni.pa165.entity.Book;
import cz.fi.muni.pa165.entity.Loan;
import cz.fi.muni.pa165.entity.Member;
import cz.fi.muni.pa165.entity.PrintedBook;
import cz.fi.muni.pa165.library.api.constants.Condition;
import cz.fi.muni.pa165.library.api.constants.Department;
import cz.fi.muni.pa165.library.api.dto.MemberDTO;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

/**
 * @author Martin Malik <374128@mail.muni.cz>
 */
@RunWith(MockitoJUnitRunner.class)
public class MemberServiceTest {

    @Mock
    private MemberDAO memberDAO;

    @Mock
    private BookDAO bookDAO;

    @InjectMocks
    private MemberServiceImpl memberService = new MemberServiceImpl();

    private Mapper mapper = new DozerBeanMapper();

    private Member member = new Member();
    private Member member2 = new Member();
    private Book book = new Book();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        memberService.setBookDAO(bookDAO);
        memberService.setMemberDAO(memberDAO);

        member.setIdMember(10l);
        member.setName("John Black");
        member.setEmail("john.black@muni.mail.cz");
        member.setAddress("Tererova 164/56,Brno");

        member2.setIdMember(9l);
        member2.setName("Lucy Red");
        member2.setAddress("1856/12, Cerna Pole, Brno");
        member2.setEmail("lucy.red@mail.muni.cz");

        book.setIdBook(9l);
        book.setName("Harry Potter");
        book.setISBN("123112315");
        book.setDescription("Book about Wizard!");
        book.setAuthors("J.K. Rowling");
        book.setDepartment(Department.Sport);

        PrintedBook pb = new PrintedBook();
        pb.setBook(book);
        pb.setCondition(Condition.Used);

        Loan loan = new Loan();
        loan.setReturned(false);
        loan.setToDate(new Date());
        loan.setFromDate(new Date());
        loan.setDateReturned(new Date());
        loan.setMember(member2);

        pb.setLoan(loan);
    }

    @Test
    public void insertMember() {
        doThrow(new RuntimeException()).when(memberDAO).insert(member2);
        try {
            memberService.insertMember(DTOEntityManager.memberEntitytoDTO(member2));
        } catch (RuntimeException re) {
        }
    }

    @Test
    public void updateMember() {
        doThrow(new RuntimeException()).when(memberDAO).update(member2);
        try {
            memberService.updateMember(DTOEntityManager.memberEntitytoDTO(member2));
        } catch (RuntimeException re) {
        }
    }

    @Test
    public void deleteMember() {
        doThrow(new RuntimeException()).when(memberDAO).delete(member2);
        try {
            memberService.deleteMember(DTOEntityManager.memberEntitytoDTO(member2));
        } catch (RuntimeException re) {
        }
    }

    @Test
    public void findMember() {
        when(memberDAO.find(member2)).thenReturn(member2);
        MemberDTO memberDTO = memberService.findMember(DTOEntityManager.memberEntitytoDTO(member2));
        assertDeepEqualsForMember(member2, memberDTO);
    }

    @Test
    public void findMemberById() {
        final long id = 9l;

        when(memberDAO.findMemberByIdMember(id)).thenReturn(member2);
        MemberDTO memberDTO = memberService.findMemberByIdMember(id);
        assertDeepEqualsForMember(member2, memberDTO);
    }

    @Test
    public void findMemberByAddress() {
        final String address = "1856/12, Cerna Pole, Brno";

        when(memberDAO.findMembersByAddress(address)).thenReturn(Arrays.asList(member2));
        List<MemberDTO> membersDTO = memberService.findMembersByAddress(address);
        List<Member> members = new ArrayList<Member>();
        members.add(member2);
        assertDeepEqualsListForMember(members, membersDTO);
    }

    @Test
    public void findMembersByEmail() {
        final String email = "lucy.red@mail.muni.cz";

        List<Member> members  = new ArrayList<Member>();
        members.add(member2);
        when(memberDAO.findMembersByEmail(email)).thenReturn(members);
        List<MemberDTO> memberDTOs = memberService.findMembersByEmail(email);
        assertDeepEqualsListForMember(members, memberDTOs);
    }

    @Test
    public void findMemberByBook() {
        when(bookDAO.find(book)).thenReturn(book);
        when(memberDAO.findMembersByBook(book)).thenReturn(Arrays.asList(member2));
        List<MemberDTO> membersDTO = memberService.findMembersByBook(DTOEntityManager.bookEntitytoDTO(book));
        List<Member> members = new ArrayList<Member>();
        members.add(member2);
        assertDeepEqualsListForMember(members, membersDTO);
    }

    private void assertDeepEqualsForMember(Member member, MemberDTO memberDTO) {
        assertEquals(member.getIdMember(), memberDTO.getIdMember());
        assertEquals(member.getName(), memberDTO.getName());
        assertEquals(member.getEmail(), memberDTO.getEmail());
        assertEquals(member.getAddress(), memberDTO.getAddress());
        assertEquals(member.getLoans(), memberDTO.getLoans());
    }

    private void assertDeepEqualsListForMember(List<Member> members, List<MemberDTO> membersDTO) {
        assertEquals(members.size(), membersDTO.size());

        Iterator it1 = members.iterator();
        Iterator it2 = membersDTO.iterator();

        while (it1.hasNext() && it2.hasNext()) {
            assertDeepEqualsForMember((Member) it1.next(), (MemberDTO) it2.next());
        }
    }
}