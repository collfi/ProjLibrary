/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.library.web;

import cz.fi.muni.pa165.datatransferobject.BookDTO;
import cz.fi.muni.pa165.datatransferobject.PrintedBookDTO;
import cz.fi.muni.pa165.service.PrintedBookServiceImpl;
import cz.fi.muni.pa165.service.api.PrintedBookService;
import java.util.ArrayList;
import java.util.List;
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

    @RequestMapping(value = "/pbook", method = RequestMethod.GET)
    public ModelAndView pbook() {
        return new ModelAndView("pbook", "command", new PrintedBookDTO());
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("library-web") PrintedBookDTO pbook,
            ModelMap model,
             @RequestParam(value="aaa", required=true) long idBook) {
        //model.addAttribute("idPrintedBook", pbook.getIdPrintedBook());
        
        //bd.setName(idBook);
        //FIND ID IN DB, SET BOOK
            //pbook.setBook(bookServiceImpl.findBookById(idBook));
        //test
        BookDTO bd = new BookDTO();
        bd.setIdBook(idBook);
        //pbook.setBook(bd);
        //model.addAttribute("book", pbook.getBook().getIdBook());
        model.addAttribute("state", pbook.getState());
        
        
        pbookService.insertPrintedBook(pbook);
        pbookService.insertPrintedBook(pbook);
        List<PrintedBookDTO> l = null;
        l = pbookService.findAllBorrowedPrintedBooks();
        if (l.size() == 2) model.addAttribute("condition", pbook);
        else model.addAttribute("condition", "SME KOKOTI");
        //model.addAttribute("state", pbook);
        //
        //model.addAttribute("idLoan", pbook.getLoan().getIdLoan());

        return "result";
    }

}

