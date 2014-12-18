/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.library.web;

import cz.fi.muni.pa165.library.api.constants.Condition;
import cz.fi.muni.pa165.library.api.dto.BookDTO;
import cz.fi.muni.pa165.library.api.dto.PrintedBookDTO;
import cz.fi.muni.pa165.library.api.service.BookService;
import cz.fi.muni.pa165.library.api.service.PrintedBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

/**
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
        pb.setCondition(Condition.New);
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
    public String editpost(@RequestParam("condition") Condition con, ModelMap model,
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

}
