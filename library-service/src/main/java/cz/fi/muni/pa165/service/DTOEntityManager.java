/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.datatransferobject.BookDTO;
import cz.fi.muni.pa165.datatransferobject.LoanDTO;
import cz.fi.muni.pa165.datatransferobject.MemberDTO;
import cz.fi.muni.pa165.datatransferobject.PrintedBookDTO;
import cz.fi.muni.pa165.entity.Book;
import cz.fi.muni.pa165.entity.Loan;
import cz.fi.muni.pa165.entity.Member;
import cz.fi.muni.pa165.entity.PrintedBook;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

/**
 *
 * @author Boris Valentovic - xvalent2
 */
public class DTOEntityManager {

    private static Mapper mapper = new DozerBeanMapper();

    public static PrintedBook printedBookDTOtoEntity(PrintedBookDTO pbookDto) {
        PrintedBook pbook = mapper.map(pbookDto, PrintedBook.class);
        return pbook;
        // dozer bean mapper
    }

    public static PrintedBookDTO printedBookEntitytoDTO(PrintedBook pbook) {
        PrintedBookDTO pbookDto = mapper.map(pbook, PrintedBookDTO.class);
        return pbookDto;
    }

    public static BookDTO bookEntitytoDTO(Book book) {
        BookDTO bookDto = mapper.map(book, BookDTO.class);
        return bookDto;
    }

    public static Book bookDTOtoEntity(BookDTO bookDto) {
        Book book = mapper.map(bookDto, Book.class);
        return book;
    }

    public static Member memberDTOtoEntity(MemberDTO memberDto) {
        Member member = mapper.map(memberDto, Member.class);
        return member;
    }

    public static MemberDTO memberEntitytoDTO(Member member) {
        MemberDTO memberDto = mapper.map(member, MemberDTO.class);
        return memberDto;
    }

    public static Loan loanDTOtoEntity(LoanDTO loanDto) {
        Loan loan = mapper.map(loanDto, Loan.class);
        return loan;
    }

    public static LoanDTO loanEntitytoDTO(Loan loan) {
        LoanDTO loanDto = mapper.map(loan, LoanDTO.class);
        return loanDto;
    }

    /*     if (pbookDto == null) {
     return null;
     }
     PrintedBook pbook = new PrintedBook();
     pbook.setBook(pbookDto.getBook());
     pbook.setCondition(pbookDto.getCondition());
     pbook.setIdPrintedBook(pbookDto.getId());
     pbook.setLoan(pbookDto.getLoan());
     pbook.setState(pbookDto.getState());
     return pbook;*/
    /*  public static PrintedBookDTO printedBookEntitytoDTO(PrintedBook pbook) {
     /*      if (pbook == null) {
     return null;
     }
     PrintedBookDTO pbookDto = new PrintedBookDTO();
     pbookDto.setBook(pbook.getBook());
     pbookDto.setCondition(pbook.getCondition());
     pbookDto.setId(pbook.getIdPrintedBook());
     pbookDto.setLoan(pbook.getLoan());
     pbookDto.setState(pbook.getState());
     return pbookDto;
     }
     */
}
