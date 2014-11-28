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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
    
    @RequestMapping(value = "/loan/addloan", method = RequestMethod.GET,
            params = {"pbookid", "bookid"})
    public String addloan(ModelMap model, 
            @RequestParam(value = "pbookid") long pbookid,   
            @RequestParam(value = "bookid") int bookid) {
        BookDTO b = bookService.findBookById(bookid);
        PrintedBookDTO pb = pbookService.findPrintedBookById(pbookid);
        List<MemberDTO> lmembers = memberService.findAllMembers();
        model.addAttribute("book", b);
        model.addAttribute("pbook", pb);
        model.addAttribute("lmembers", lmembers);
        return "addloan";
    }
    
    @RequestMapping(value = "/loan/addloan", method = RequestMethod.POST)
    public String postloan(ModelMap model, 
                @RequestParam("pbookid") long pbookid,
                @RequestParam("bookid") int bookid, 
                @RequestParam("memberid") int memberid,
                @RequestParam("datetill") Date datetill) {

        MemberDTO m = memberService.findMemberByIdMember(memberid);
        Set<PrintedBookDTO> pbs = new HashSet<>();
        pbs.add(pbookService.findPrintedBookById(pbookid));

        LoanDTO l = new LoanDTO();
        l.setMember(m);
        l.setPbooks(pbs);
        l.setToDate(datetill);
        l.setFromDate(new Date());
        loanService.insertLoan(l);
                  
        return "redirect:/loan/listloans";
    }

    @RequestMapping(value = "/loan/listloans", method = RequestMethod.GET)
    public String listloans(ModelMap model) {
        List<LoanDTO> list = loanService.findAllLoans();
//        for (LoanDTO i : list) {
//            for (PrintedBookDTO j : i.getPbooks()) {
//                System.out.println("TUT PBOOK" + j);
//            }
//        }
        model.addAttribute("list", list);
        return "listloans";
    }
    
    @RequestMapping(value = "/loan/findloans", method = RequestMethod.GET)
    public ModelAndView findloans(ModelMap model) {
        ModelAndView mav = new ModelAndView("findloans");

        mav.addObject("search", new SearchModel());

        return mav;
    }
    
    @RequestMapping(value = "/loan/findloans", method = RequestMethod.POST)
    private ModelAndView search(@ModelAttribute SearchModel search) {
        ModelAndView mav = new ModelAndView("findloans");

        mav.addObject("search", search);
        System.out.println(search.getInput());
//        if (search.getSearch().equals("date")) {
//            mav.addObject("list", loanService.findAllLoandsFromTo());
//        } 
        
//        else if (search.getSearch().equals("Name") || search.getSearch().equals("Názov")) {
//            mav.addObject("list", bookService.findBooksByName(search.getInput()));
//        } else if (search.getSearch().equals("Authors") || search.getSearch().equals("Autori")) {
//            mav.addObject("list", bookService.findBooksByAuthor(search.getInput()));
//        }

        return mav;
    }
    
    @RequestMapping(value="/loan/delete/{id}", params={"id"})
    public String delete(ModelMap model, @RequestParam(value ="id") int id) {
        loanService.deleteLoan(loanService.findLoanById(id));
        return "redirect:/loan/listloans";
    }


}
