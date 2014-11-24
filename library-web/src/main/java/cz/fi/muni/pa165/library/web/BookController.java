package cz.fi.muni.pa165.library.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/book")
public class BookController {
 
	@RequestMapping(method = RequestMethod.GET)
	public String show(ModelMap model) {
 
		//model.addAttribute("message", "this is my book!");
		return "book";
 
	}
}
