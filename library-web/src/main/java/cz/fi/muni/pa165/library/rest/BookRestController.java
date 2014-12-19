/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.library.rest;

import cz.fi.muni.pa165.library.api.constants.Department;
import cz.fi.muni.pa165.library.api.dto.BookDTO;
import cz.fi.muni.pa165.library.api.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Rest-Api controller for books.
 *
 * @author michal.lukac
 */
@RestController
public class BookRestController {

    @Autowired
    public BookService bookService;

    @RequestMapping(value = "/api/book/find", method = RequestMethod.GET, produces = "application/json")
    public List<BookDTO> apiFindBooks(@RequestParam(value = "name", defaultValue = "") String name,
                                      @RequestParam(value = "authors", defaultValue = "") String authors,
                                      @RequestParam(value = "isbn", defaultValue = "") String isbn,
                                      @RequestParam(value = "department", defaultValue = "") String department) {
        List<BookDTO> bookList = new ArrayList<>();

        try {
            if (name.length() > 0) {
                bookList = bookService.findBooksByName(name);
            } else if (authors.length() > 0) {
                bookList = bookService.findBooksByAuthor(authors);
            } else if (isbn.length() > 0) {
                bookList = bookService.findBooksByISBN(isbn);
            } else if (department.length() > 0) {
                Department d = Department.valueOf(department);
                bookList = bookService.findBooksByDepartment(d);
            }
            return bookList;
        } catch (IllegalArgumentException | NullPointerException e) {
            return null;
        }
    }

    @RequestMapping(value = "/api/book/delete/{number}", method = RequestMethod.GET, produces = "application/json")
    public BookDTO apiDeleteBook(ModelMap model, @PathVariable("number") int number) {
        BookDTO book = bookService.findBookById(number);
        bookService.deleteBook(book);
        return book;
    }

    @RequestMapping(value = "/api/book/add/", method = RequestMethod.POST)
    public String apiSaveBook(@RequestBody @Valid BookDTO book) {
        bookService.insertBook(book);
        return "Saved book: " + book.toString();
    }

    @RequestMapping(value = "/api/book/update/", method = RequestMethod.POST)
    public String apiUpdateBook(@RequestBody @Valid BookDTO book) {
        bookService.updateBook(book);
        return "Saved person: " + book.toString();
    }
}
