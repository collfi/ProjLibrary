/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.library.web;

import cz.fi.muni.pa165.library.api.dto.BookDTO;
import cz.fi.muni.pa165.library.api.dto.LoanDTO;
import cz.fi.muni.pa165.library.api.dto.MemberDTO;
import cz.fi.muni.pa165.library.api.dto.PrintedBookDTO;
import cz.fi.muni.pa165.library.api.service.BookService;
import cz.fi.muni.pa165.library.api.service.LoanService;
import cz.fi.muni.pa165.library.api.service.MemberService;
import cz.fi.muni.pa165.library.api.service.PrintedBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
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
    public ModelAndView addloanstep0(ModelMap model) {
        ModelAndView mav = new ModelAndView("addloan");

        mav.addObject("search", new SearchModel());
        mav.addObject("lmembers", memberService.findAllMembers());
        mav.addObject("lbooks", bookService.findAllBooks());
        return mav;
    }

    @RequestMapping(value = "/loan/addloan/member", method = RequestMethod.POST)
    public String addloanstep1(@ModelAttribute SearchModel search, RedirectAttributes redirectAttributes,
            @RequestParam("dateto") String dateto) {
        if (search.getSearch() == null) {
            redirectAttributes.addFlashAttribute("error", "validationmissing");
            return "redirect:/loan/addloan";
        }
        LoanDTO loan = new LoanDTO();
        loan.setReturned(Boolean.FALSE);
        loan.setDescription("description");
        loan.setFromDate(new Date());

        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        Date date;
        try {
            date = formatter.parse(dateto);
        } catch (ParseException e) {
            redirectAttributes.addFlashAttribute("error", "wrongdate");
            return "redirect:/loan/addloan";
        }
        loan.setToDate(date);
        if (loan.getFromDate().after(date)) {
            redirectAttributes.addFlashAttribute("error", "wrongdate");
            return "redirect:/loan/addloan";
        }
        List<PrintedBookDTO> pbooks = pbookService.findPrintedBookByState(bookService.findBookById(Long.parseLong(search.getBook())), Boolean.FALSE);
        if (pbooks.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "nofreebook");
            return "redirect:/loan/addloan";
        }

        PrintedBookDTO pbook = pbooks.get(0);
        loan.setPrintedBook(pbook);

        MemberDTO member = memberService.findMemberByIdMember(Long.parseLong(search.getSearch(), 10));

        loan.setMember(member);
        pbook.setLoan(loan);
        pbook.setState(Boolean.TRUE);

        loanService.insertLoan(loan);
        pbookService.updatePrintedBook(pbook);

        HashSet set = new HashSet();
        set.add(loan);
        member.setLoans(set);
        memberService.updateMember(member);

        return "redirect:/loan/listloans";
    }

    @RequestMapping(value = "/loan/listloans", method = RequestMethod.GET)
    public String listLoans(ModelMap model) {
        List<LoanDTO> loans = loanService.findAllLoans();
        model.addAttribute("loans", loans);
        return "listloans";
    }

    @RequestMapping(value = "/loan/findloans", method = RequestMethod.GET)
    public ModelAndView findLoans(ModelMap model) {
        ModelAndView mav = new ModelAndView("findloans");

        mav.addObject("search", new SearchModel());

        return mav;
    }

    @RequestMapping(value = "/loan/findloans/result")
    private ModelAndView processSearch(@ModelAttribute SearchModel search) {

        ModelAndView mav = new ModelAndView("findloans");
        if (search.getSearch() == null) {
            mav.addObject("search", new SearchModel());
            mav.addObject("list", new ArrayList<BookDTO>());
            return mav;
        }
        mav.addObject("search", search);

        try {
            if (search.getSearch().equals("Member")) {
                List<MemberDTO> list = memberService.findMembersByName(search.getInput());
                List<LoanDTO> l2 = loanService.findAllLoansByMember(list.get(0), false);
                if (!list.isEmpty()) {
                    mav.addObject("loans", l2);
                }
                return mav;
            } else if (search.getSearch().equals("Id")) {
                List<LoanDTO> l2 = new ArrayList<LoanDTO>();
                l2.add(loanService.findLoanById(Integer.parseInt(search.getInput())));
                mav.addObject("loans", l2);
                return mav;
            } else if (search.getSearch().equals("Book")) {
                List<BookDTO> list = bookService.findBooksByName(search.getInput());
                List<LoanDTO> l2 = loanService.findAllLoansWithBook(list.get(0));

                if (!list.isEmpty()) {
                    mav.addObject("loans", l2);
                    return mav;
                }
            }
        } catch (Exception e) {

            return mav;
        }

        return mav;
    }

    @RequestMapping(value = "/loan/delete/{loanid}")
    public String delete(@PathVariable("loanid") int id) {
        LoanDTO loan = loanService.findLoanById(id);
        PrintedBookDTO pbook = pbookService.findPrintedBook(loan.getPrintedBook());
        pbook.setState(Boolean.FALSE);
        pbook.setLoan(null);

        MemberDTO member = memberService.findMember(loan.getMember());
        for (LoanDTO loan1 : member.getLoans()) {
            if (loan1.getIdLoan() == id) {
                member.getLoans().remove(loan1);
            }
        }

        pbookService.updatePrintedBook(pbook);
        memberService.updateMember(member);
        loanService.deleteLoan(loan);

        return "redirect:/loan/listloans";
    }

    @RequestMapping(value = "/loan/setreturned/{loanid}", method = RequestMethod.GET)
    public String listSetReturned(@PathVariable("loanid") int id) {
        LoanDTO l = loanService.findLoanById(id);
        l.setDateReturned(new Date());
        l.setReturned(true);

        PrintedBookDTO pbook = pbookService.findPrintedBook(l.getPrintedBook());
        pbook.setState(Boolean.FALSE);
        pbook.setLoan(null);

        pbookService.updatePrintedBook(pbook);
        loanService.updateLoan(l);

        return "redirect:/loan/listloans";
    }

    @RequestMapping("/loan/id/{number}")
    public String showloan(ModelMap model, @PathVariable("number") int number) {
        LoanDTO loan = loanService.findLoanById(number);
        model.addAttribute("loan", loan);
        return "showloan";
    }

}
