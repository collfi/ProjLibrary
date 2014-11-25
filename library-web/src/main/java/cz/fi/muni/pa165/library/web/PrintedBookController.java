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
    PrintedBookService pbookService;

    @RequestMapping(value = "/pbook", method = RequestMethod.GET)
    public ModelAndView pbook() {
        return new ModelAndView("pbook", "command", new PrintedBookDTO());
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("library-web") PrintedBookDTO pbook,
            ModelMap model,
             @RequestParam(value="aaa", required=true) long idBook) {
        model.addAttribute("idPrintedBook", pbook.getIdPrintedBook());
        
        //bd.setName(idBook);
        //FIND ID IN DB, SET BOOK
            //pbook.setBook(bookServiceImpl.findBookById(idBook));
        //test
        BookDTO bd = new BookDTO();
        bd.setIdBook(idBook);
        pbook.setBook(bd);
        model.addAttribute("book", pbook.getBook().getIdBook());
        
        
        pbookService.insertPrintedBook(pbook);
        
        model.addAttribute("state", pbook.getState());
        model.addAttribute("condition", String.valueOf(pbook.getCondition()));
        //model.addAttribute("idLoan", pbook.getLoan().getIdLoan());

        return "result";
    }

}

