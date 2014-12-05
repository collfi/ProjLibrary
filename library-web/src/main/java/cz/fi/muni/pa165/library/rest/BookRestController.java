/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.library.rest;

import cz.fi.muni.pa165.datatransferobject.BookDTO;
import cz.fi.muni.pa165.service.api.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest-Api controller for books.
 * @author michal.lukac
 */
@RestController 
public class BookRestController {

    @Autowired
    public BookService bookService;
    
    @RequestMapping(value="/api/book/{number}", method=RequestMethod.GET, produces = "application/json")
    public BookDTO apibookget(ModelMap model, @PathVariable("number") int number)
    {
        BookDTO book = bookService.findBookById(number);
        return book;
    }
    
    // add
    // delete
    // add printedbook
    // update
}
