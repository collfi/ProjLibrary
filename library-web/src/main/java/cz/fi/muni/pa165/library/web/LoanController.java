/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.library.web;

import cz.fi.muni.pa165.datatransferobject.BookDTO;
import cz.fi.muni.pa165.datatransferobject.LoanDTO;
import cz.fi.muni.pa165.datatransferobject.MemberDTO;
import cz.fi.muni.pa165.datatransferobject.PrintedBookDTO;
import cz.fi.muni.pa165.service.api.BookService;
import cz.fi.muni.pa165.service.api.LoanService;
import cz.fi.muni.pa165.service.api.MemberService;
import cz.fi.muni.pa165.service.api.PrintedBookService;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


/**
 *
 * @author Sergii Pylypenko - xpylypen
 */
@Controller
public class LoanController {
    @Autowired
    public PrintedBookService pbookService;

    @Autowired
    public BookService bookService;
    
    @Autowired
    public LoanService loanService;
    
    @Autowired
    public MemberService memberService;
    
    @RequestMapping(value = "/loan/addloan", method = RequestMethod.GET)
        public ModelAndView addloanstep0(ModelMap model)
        {
            ModelAndView mav = new ModelAndView("addloan");

            mav.addObject("search", new SearchModel());
            mav.addObject("lmembers", memberService.findAllMembers());
            mav.addObject("lbooks", bookService.findAllBooks());
            return mav;
        }

    @RequestMapping(value = "/loan/addloan/member", method = RequestMethod.POST)
        public String addloanstep1(@ModelAttribute SearchModel search) {
            LoanDTO loan = new LoanDTO();
            loan.setReturned(false);
            loan.setDescription("description");
            loan.setFromDate(new Date());
            loan.setToDate(new Date());
            
            List<PrintedBookDTO> pbooks = pbookService.findPrintedBooksByBook(bookService.findBookById(Long.parseLong(search.getBook())));
            PrintedBookDTO pbook = pbooks.get(0);
            loan.setPrintedBook(pbook);
            
            MemberDTO member = memberService.findMemberByIdMember(Long.parseLong(search.getSearch(), 10));
            
            loan.setMember(member);
            pbook.setLoan(loan);
            
            loanService.insertLoan(loan);
            pbookService.updatePrintedBook(pbook);
            
            HashSet set = new HashSet();
            set.add(loan);
            member.setLoans(set);
            memberService.updateMember(member);
            
            return "redirect:/";
    }
    
    @RequestMapping(value = "/loan/listloans", method = RequestMethod.GET)
    public String listLoans(ModelMap model) {
        List<LoanDTO> loans = loanService.findAllLoans();
        model.addAttribute("loans", loans);
        return "listloans";
    }

    @RequestMapping(value = "/loan/findloans", method = RequestMethod.GET)
    public String findLoans(ModelMap model) {
        List<MemberDTO> lmembers = memberService.findAllMembers();
        model.addAttribute("lmembers", lmembers);
        List<BookDTO> books = bookService.findAllBooks();
        model.addAttribute("books", books);
        return "findloans";
    }
    
    @RequestMapping(value = "/loan/findloans/date", method = RequestMethod.POST)
    private String searchByDate(ModelMap model, 
                @RequestParam("datefrom") Date datefrom,
                @RequestParam("dateto") Date dateto) {

        model.addAttribute("loans", loanService.findAllLoandsFromTo(datefrom, dateto));
        model.addAttribute("datefrom", datefrom);
        model.addAttribute("dateto", dateto);
        List<MemberDTO> lmembers = memberService.findAllMembers();
        model.addAttribute("lmembers", lmembers);
        List<BookDTO> books = bookService.findAllBooks();
        model.addAttribute("books", books);

        return "findloans";
    }


    @RequestMapping(value = "/loan/findloans/member", method = RequestMethod.POST)
    private String searchByDate(ModelMap model,
                                @RequestParam("memberid") int memberid,
                                @RequestParam("returned") boolean returned) {

        MemberDTO m = new MemberDTO();
        m.setIdMember(memberid);
        model.addAttribute("loans", loanService.findAllLoansByMember(m, returned));
        List<MemberDTO> lmembers = memberService.findAllMembers();
        List<BookDTO> books = bookService.findAllBooks();
        model.addAttribute("books", books);
        model.addAttribute("lmembers", lmembers);
        model.addAttribute("memberid", lmembers);
        model.addAttribute("returned", lmembers);
        return "findloans";
    }

    @RequestMapping(value = "/loan/findloans/book", method = RequestMethod.POST)
    private String searchByDate(ModelMap model, @RequestParam("bookid") int bookid) {

        BookDTO b = new BookDTO();
        b.setIdBook(bookid);
        model.addAttribute("loans", loanService.findAllLoansWithBook(b));
        List<MemberDTO> lmembers = memberService.findAllMembers();
        List<BookDTO> books = bookService.findAllBooks();
        model.addAttribute("books", books);
        model.addAttribute("lmembers", lmembers);
        model.addAttribute("memberid", lmembers);
        model.addAttribute("bookid", bookid);
        return "findloans";
    }

    
    @RequestMapping(value="/loan/delete/{loanid}")
    public String delete(@PathVariable("loanid") int id) {
        loanService.deleteLoan(loanService.findLoanById(id));
        return "redirect:/loan/listloans";
    }

    @RequestMapping(value = "/loan/setreturned/{loanid}", method = RequestMethod.GET)
    public String listSetReturned(@RequestParam("loanid") int loanid) {
        LoanDTO l = new LoanDTO();
        l.setIdLoan(loanid);
        l.setDateReturned(new Date());
        l.setReturned(true);
        loanService.updateLoan(l);
        return "redirect:/loan/listloans";
    }


}
