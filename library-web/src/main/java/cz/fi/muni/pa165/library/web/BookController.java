package cz.fi.muni.pa165.library.web;

import cz.fi.muni.pa165.datatransferobject.BookDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class BookController{
 
    	@RequestMapping(value = "/book",method = RequestMethod.GET)
	public String show(ModelMap model) {
 
		//model.addAttribute("message", "this is my book!");
		return "book";
 
	}
        
       @RequestMapping(value = "/book/add", method = RequestMethod.POST)
       public String addStudent(@ModelAttribute("SpringWeb")BookDTO book, ModelMap model) {
       
            model.addAttribute("message", book.getName());
      
            return "result";
        }
        
        
}
