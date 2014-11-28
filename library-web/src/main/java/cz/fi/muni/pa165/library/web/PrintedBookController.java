/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.library.web;

import cz.fi.muni.pa165.datatransferobject.BookDTO;
import cz.fi.muni.pa165.datatransferobject.PrintedBookDTO;
import cz.fi.muni.pa165.entity.Book;
import cz.fi.muni.pa165.entity.PrintedBook;
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
import org.springframework.web.bind.annotation.PathVariable;
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

    @RequestMapping(value = "/pbook/management", method = RequestMethod.GET)
    public String pbookmanagement(ModelMap model) {
        return "pbookmanagement";
    }

    @RequestMapping(value = "/pbook/addformular/{number}", method = RequestMethod.GET)
    public String addformular(ModelMap model, @PathVariable("number") int number) {
        PrintedBookDTO pb = new PrintedBookDTO();
        pb.setCondition(PrintedBook.Condition.New);
        pb.setState(Boolean.FALSE);

        BookDTO b = bookService.findBookById(number);
        pb.setBook(b);
        pbookService.insertPrintedBook(pb);

        Set<PrintedBookDTO> set = b.getBooks();
        set.add(pb);
        b.setBooks(set);
        bookService.updateBook(b);

        return "redirect:/book/id/" + String.valueOf(number);
    }

    @RequestMapping("/pbook/edit/{number}")
    public String editbook(ModelMap model, @PathVariable("number") long number) {
        PrintedBookDTO pbook = pbookService.findPrintedBookById(number);
        model.addAttribute("pbook", pbook);
        return "editpbook";
    }

    @RequestMapping(value = "/pbook/editpost", method = RequestMethod.POST)
    public String editpost(@RequestParam("condition") PrintedBook.Condition con, ModelMap model,
            @RequestParam("idPrintedBook") long idpbook) {
        PrintedBookDTO pb = pbookService.findPrintedBookById(idpbook);
        pb.setCondition(con);
        pbookService.updatePrintedBook(pb);
        return "redirect:/book/id/" + String.valueOf(pb.getBook().getIdBook());
    }
    
    @RequestMapping("/pbook/delete/{number}")
    public String deletebook(ModelMap model, @PathVariable("number") long number) {
        PrintedBookDTO pbook = pbookService.findPrintedBookById(number);
        pbookService.deletePrintedBook(pbook);
        return "redirect:/book/id/" + String.valueOf(pbook.getBook().getIdBook());
    }

    
    //OLD!!
//    @RequestMapping(value = "/pbook", method = RequestMethod.GET)
//    public ModelAndView pbook() {
//        return new ModelAndView("pbook", "command", new PrintedBookDTO());
//    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("pa165") PrintedBookDTO pbook,
            ModelMap model,
            @RequestParam(value = "idBook", required = true) long idBook,
            @RequestParam(value = "idLoan", required = true) long idLoan) {

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
    public String showAllBorrowed(ModelMap model) {
        model.addAttribute("list", pbookService.findAllBorrowedPrintedBooks());
        return "show";
    }

}
