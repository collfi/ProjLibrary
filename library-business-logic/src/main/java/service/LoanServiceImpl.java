package service;

import cz.fi.muni.pa165.dao.BookDAO;
import cz.fi.muni.pa165.dao.LoanDAO;
import cz.fi.muni.pa165.dao.MemberDAO;
import cz.fi.muni.pa165.entity.Book;
import cz.fi.muni.pa165.entity.Loan;
import cz.fi.muni.pa165.entity.Member;
import cz.fi.muni.pa165.library.api.dto.BookDTO;
import cz.fi.muni.pa165.library.api.dto.LoanDTO;
import cz.fi.muni.pa165.library.api.dto.MemberDTO;
import cz.fi.muni.pa165.library.api.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Service layer class of Loan implementation
 *
 * @author Sergii Pylypenko, 430519
 */
@Service
@Transactional
public class LoanServiceImpl implements LoanService {

    @Autowired
    private LoanDAO loanDao;

    @Autowired
    private BookDAO bookDao;

    @Autowired
    private MemberDAO memberDao;

    public void setLoanDao(LoanDAO loanDao) {
        this.loanDao = loanDao;
    }

    public void setMemberDao(MemberDAO memberDao) {
        this.memberDao = memberDao;
    }

    public void setBookDao(BookDAO bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public void insertLoan(LoanDTO loanto) {
        Loan loan = DTOEntityManager.loanDTOtoEntity(loanto);
        loanDao.insert(loan);
    }

    @Override
    public void updateLoan(LoanDTO loanto) {
        Loan loan = DTOEntityManager.loanDTOtoEntity(loanto);
        loanDao.update(loan);
    }

    @Override
    public void deleteLoan(LoanDTO loanto) {
        Loan loan = DTOEntityManager.loanDTOtoEntity(loanto);
        loanDao.delete(loan);
    }

    @Override
    public LoanDTO findLoan(LoanDTO loanto) {
        Loan loan = DTOEntityManager.loanDTOtoEntity(loanto);
        return DTOEntityManager.loanEntitytoDTO(loanDao.find(loan));
    }

    @Override
    public LoanDTO findLoanById(int id) {
        Loan l = loanDao.findLoanById(id);
        return DTOEntityManager.loanEntitytoDTO(l);
    }

    private List<LoanDTO> entitiesToDTOs(List<Loan> loans) {
        List<LoanDTO> ldtos = new ArrayList<>();
        for (Loan l : loans) {
            ldtos.add(DTOEntityManager.loanEntitytoDTO(l));
        }
        return ldtos;
    }

    @Override
    public List<LoanDTO> findAllLoansByMember(MemberDTO memdto, boolean isReturned) {
        Member m = memberDao.find(DTOEntityManager.memberDTOtoEntity(memdto));
        return entitiesToDTOs(loanDao.findAllLoansByMember(m, isReturned));
    }

    @Override
    public List<LoanDTO> findAllLoandsFromTo(Date from, Date to) {
        return entitiesToDTOs(loanDao.findAllLoandsFromTo(from, to));
    }

    @Override
    public List<LoanDTO> findAllLoansWithBook(BookDTO book) {
        Book b = bookDao.find(DTOEntityManager.bookDTOtoEntity(book));
        return entitiesToDTOs(loanDao.findAllLoansWithBook(b));
    }

    @Override
    public List<LoanDTO> findAllLoans() {
        return entitiesToDTOs(loanDao.findAllLoans());
    }
}
