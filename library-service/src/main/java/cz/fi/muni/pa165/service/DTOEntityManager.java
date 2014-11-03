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
 *Mapper that maps data transfer objects to entities and vice versa
 * @author Boris Valentovic - xvalent2
 */
public class DTOEntityManager {

    private static Mapper mapper = new DozerBeanMapper();

    public static PrintedBook printedBookDTOtoEntity(PrintedBookDTO pbookDto) {
        if (pbookDto == null) {
            return null;
        }
        PrintedBook pbook = mapper.map(pbookDto, PrintedBook.class);
        return pbook;
    }

    public static PrintedBookDTO printedBookEntitytoDTO(PrintedBook pbook) {
        if (pbook == null) {
            return null;
        }
        PrintedBookDTO pbookDto = mapper.map(pbook, PrintedBookDTO.class);
        return pbookDto;
    }

    public static BookDTO bookEntitytoDTO(Book book) {
        if (book == null) {
            return null;
        }
        BookDTO bookDto = mapper.map(book, BookDTO.class);
        return bookDto;
    }

    public static Book bookDTOtoEntity(BookDTO bookDto) {
        if (bookDto == null) {
            return null;
        }
        Book book = mapper.map(bookDto, Book.class);
        return book;
    }

    public static Member memberDTOtoEntity(MemberDTO memberDto) {
        if (memberDto == null) {
            return null;
        }
        Member member = mapper.map(memberDto, Member.class);
        return member;
    }

    public static MemberDTO memberEntitytoDTO(Member member) {
        if (member == null) {
            return null;
        }
        MemberDTO memberDto = mapper.map(member, MemberDTO.class);
        return memberDto;
    }

    public static Loan loanDTOtoEntity(LoanDTO loanDto) {
        if (loanDto == null) {
            return null;
        }
        Loan loan = mapper.map(loanDto, Loan.class);
        return loan;
    }

    public static LoanDTO loanEntitytoDTO(Loan loan) {
        if (loan == null) {
            return null;
        }
        LoanDTO loanDto = mapper.map(loan, LoanDTO.class);
        return loanDto;
    }
}
