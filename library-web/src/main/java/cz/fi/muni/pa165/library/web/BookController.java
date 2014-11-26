package cz.fi.muni.pa165.library.web;

import cz.fi.muni.pa165.datatransferobject.BookDTO;
import cz.fi.muni.pa165.datatransferobject.PrintedBookDTO;
import cz.fi.muni.pa165.service.api.BookService;
import cz.fi.muni.pa165.service.api.PrintedBookService;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class BookController{
        @Autowired
        public PrintedBookService pbookService;
    
        @Autowired
        public BookService bookService;

        @RequestMapping(value = "/book/management",method = RequestMethod.GET)
	public String bookmanagement(ModelMap model) {
		return "bookmanagement";
	}
        
    	@RequestMapping(value = "/book/addformular",method = RequestMethod.GET)
	public String addformular(ModelMap model) {
		return "addbook";
	}
        
        @RequestMapping(value = "/book/addpost", method = RequestMethod.POST)
        public String addpost(@ModelAttribute("library-web")BookDTO book, ModelMap model) {
            model.addAttribute("name", book.getName());

            bookService.insertBook(book);
            List<BookDTO> list =bookService.findAllBooks();
            model.addAttribute("list", list);
                    
            return "showbooks";
        }
        
       	@RequestMapping(value = "/book/showbooks",method = RequestMethod.GET)
	public String showbooks(ModelMap model) {
            
            List<BookDTO> list =bookService.findAllBooks();
            model.addAttribute("list", list);
            
            return "showbooks";
	}
        
        @RequestMapping("/book/id/{number}")
        public String showbook(ModelMap model, @PathVariable("number") int number)
        {
            BookDTO book = bookService.findBookById(number);
            //List<PrintedBookDTO> list = pbookService.findPrintedBooksByBook(book);
            model.addAttribute("book", book);
            //model.addAttribute()
            
            return "showbook";
        }
        
        @RequestMapping("/book/edit/{number}")
        public String editbook(ModelMap model, @PathVariable("number") int number)
        {
            BookDTO book = bookService.findBookById(number);
            model.addAttribute("book", book);
            
            return "editbook";
        }
        
        @RequestMapping(value = "/book/editpost", method = RequestMethod.POST)
        public String editpost(@ModelAttribute("library-web")BookDTO book, ModelMap model) {
            model.addAttribute("name", book.getName());

            bookService.updateBook(book);
            List<BookDTO> list = bookService.findAllBooks();
            model.addAttribute("list", list);
                    
            return "showbooks";
        }

}
