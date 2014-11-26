package cz.fi.muni.pa165.library.web;

import cz.fi.muni.pa165.datatransferobject.BookDTO;
import cz.fi.muni.pa165.service.api.BookService;
import cz.fi.muni.pa165.service.api.PrintedBookService;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class BookController{
 
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

                    
            return "showbook";
        }
        
       	@RequestMapping(value = "/book/showbooks",method = RequestMethod.GET)
	public String showbook(ModelMap model) {
            
            List<BookDTO> list =bookService.findAllBooks();
            
            List<String> l = new ArrayList<String>();
            l.add("ahoj");
            l.add("collfi");
            model.addAttribute("list", l);
            
            return "showbooks";
	}
}
