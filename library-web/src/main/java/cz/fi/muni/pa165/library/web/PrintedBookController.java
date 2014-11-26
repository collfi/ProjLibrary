/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.library.web;

import cz.fi.muni.pa165.datatransferobject.BookDTO;
import cz.fi.muni.pa165.datatransferobject.PrintedBookDTO;
import cz.fi.muni.pa165.entity.Book;
import cz.fi.muni.pa165.service.PrintedBookServiceImpl;
import cz.fi.muni.pa165.service.api.BookService;
import cz.fi.muni.pa165.service.api.PrintedBookService;
import java.util.ArrayList;
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
 * @author Boris Valentovic - xvalent2
 */
@Controller
public class PrintedBookController {

    @Autowired
    public PrintedBookService pbookService;

    @Autowired
    public BookService bookService;

    @RequestMapping(value = "/pbook", method = RequestMethod.GET)
    public ModelAndView pbook() {
        return new ModelAndView("pbook", "command", new PrintedBookDTO());
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("library-web") PrintedBookDTO pbook,
            ModelMap model,
            @RequestParam(value = "aaa", required = true) long idBook) {
        //model.addAttribute("idPrintedBook", pbook.getIdPrintedBook());

        //bd.setName(idBook);
        //FIND ID IN DB, SET BOOK
        //pbook.setBook(bookServiceImpl.findBookById(idBook));
        //test
        
        //model.addAttribute("book", pbook.getBook().getIdBook());
        model.addAttribute("state", pbook.getState());

        pbookService.insertPrintedBook(pbook);
        pbookService.insertPrintedBook(pbook);
        List<PrintedBookDTO> l = null;
        PrintedBookDTO q = pbookService.findPrintedBookById(1l);
        l = pbookService.findAllBorrowedPrintedBooks();
        model.addAttribute("condition", q);

        //model.addAttribute("state", pbook);
        //
        //model.addAttribute("idLoan", pbook.getLoan().getIdLoan());
        /*BookDTO bd = new BookDTO();
        //bd.setIdBook(idBook);
        pbook.setBook(bd);
        Set<PrintedBookDTO> h = new HashSet<>();
        //h.add(pbookService.findPrintedBookById(1l));
        bd.setBooks(h);
        bd.setAuthors("author");
        bd.setDepartment(Book.Department.Sport);
        bd.setDescription("ADS");
        bd.setISBN("324432");
        bd.setName("NAME");
        bookService.insertBook(bd);*/
        
        return "result";
    }
    
    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public String showAll(ModelMap model){
        //for (PrintedBookDTO pb: pbookService.findAllBorrowedPrintedBooks()) {
            model.addAttribute("list", pbookService.findAllBorrowedPrintedBooks());
            
        //}
        

       
        return "show";
    }

}
