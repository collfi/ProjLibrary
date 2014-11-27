package cz.fi.muni.pa165.library.web;

import cz.fi.muni.pa165.DAException;
import cz.fi.muni.pa165.datatransferobject.BookDTO;
import cz.fi.muni.pa165.datatransferobject.PrintedBookDTO;
import cz.fi.muni.pa165.service.api.BookService;
import cz.fi.muni.pa165.service.api.PrintedBookService;
import java.util.HashSet;
import java.util.List;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


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
        
    	@RequestMapping(value = "/book/addformular", method = RequestMethod.GET)
	public String addformular(ModelMap model) {
		return "addbook";
	}
        
        @RequestMapping(value = "/book/addpost", method = RequestMethod.POST)
        public String addpost(@ModelAttribute("pa165") @Valid BookDTO book, BindingResult bindingResult, ModelMap model,
                RedirectAttributes redirectAttributes) {
            if (bindingResult.hasErrors()) {
                
                redirectAttributes.addFlashAttribute("name", book.getName());
                redirectAttributes.addFlashAttribute("isbn", book.getISBN());
                redirectAttributes.addFlashAttribute("authors", book.getAuthors());
                redirectAttributes.addFlashAttribute("description", book.getDescription());
                redirectAttributes.addFlashAttribute("error", "missing");
                return "redirect:/book/addformular";
            }
            model.addAttribute("name", book.getName());
            book.setBooks(new HashSet<PrintedBookDTO>());
            try {
                bookService.insertBook(book);
            } catch (DAException dae) {
                redirectAttributes.addFlashAttribute("name", book.getName());
                redirectAttributes.addFlashAttribute("isbn", book.getISBN());
                redirectAttributes.addFlashAttribute("authors", book.getAuthors());
                redirectAttributes.addFlashAttribute("description", book.getDescription());
                redirectAttributes.addFlashAttribute("error", "duplicate");
                return "redirect:/book/addformular";
            }
            List<BookDTO> list = bookService.findAllBooks();
            model.addAttribute("list", list);
                    
            return "redirect:/book/showbooks";
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
            List<PrintedBookDTO> list = pbookService.findPrintedBooksByBook(book);
            model.addAttribute("book", book);
            model.addAttribute("list", list);
            
            return "showbook";
        }
        
        @RequestMapping("/book/edit/{number}")
        public String editbook(ModelMap model, @PathVariable("number") int number)
        {
            BookDTO book = bookService.findBookById(number);
            model.addAttribute("book", book);
            
            return "editbook";
        }
        
        @RequestMapping("/book/delete/{number}")
        public String deletepost(ModelMap model, @PathVariable("number") int number)
        {
            bookService.deleteBook(bookService.findBookById(number));
            List<BookDTO> list = bookService.findAllBooks();
            model.addAttribute("list", list);
                    
            return "redirect:/book/showbooks";
        }
        
        @RequestMapping(value = "/book/editpost", method = RequestMethod.POST)
        public String editpost(@ModelAttribute("pa165") @Valid BookDTO book, BindingResult bindingResult, ModelMap model, 
                RedirectAttributes redirectAttributes) {
//            BookDTO bookNew = bookService.findBookById(book.getIdBook());
            if (bindingResult.hasErrors()) {         
                redirectAttributes.addFlashAttribute("name", book.getName());
                redirectAttributes.addFlashAttribute("isbn", book.getISBN());
                redirectAttributes.addFlashAttribute("authors", book.getAuthors());
                redirectAttributes.addFlashAttribute("description", book.getDescription());
                redirectAttributes.addFlashAttribute("error", "missing");
                return "redirect:/book/edit/" + String.valueOf(book.getIdBook());
            }
            try {
                bookService.updateBook(book);
            } catch (JpaSystemException jse) {
                redirectAttributes.addFlashAttribute("name", book.getName());
                redirectAttributes.addFlashAttribute("isbn", book.getISBN());
                redirectAttributes.addFlashAttribute("authors", book.getAuthors());
                redirectAttributes.addFlashAttribute("description", book.getDescription());
                redirectAttributes.addFlashAttribute("error", "duplicate");
                return "redirect:/book/edit/" + String.valueOf(book.getIdBook());
            }
            List<BookDTO> list = bookService.findAllBooks();
            model.addAttribute("list", list);
                    
            return "redirect:/book/id/" + String.valueOf(book.getIdBook());
        }

        @RequestMapping("/book/findbooks")
        public String findbooks(ModelMap model)
        {
            List<BookDTO> list = bookService.findAllBooks();
            model.addAttribute("list", list);
                    
            return "findbooks";
        }
}
