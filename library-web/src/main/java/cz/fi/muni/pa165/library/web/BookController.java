package cz.fi.muni.pa165.library.web;

import cz.fi.muni.pa165.library.api.constants.Department;
import cz.fi.muni.pa165.library.api.dto.BookDTO;
import cz.fi.muni.pa165.library.api.dto.PrintedBookDTO;
import cz.fi.muni.pa165.library.api.service.BookService;
import cz.fi.muni.pa165.library.api.service.PrintedBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import cz.fi.muni.pa165.dao.DuplicationException;

/**
 * @author michal.lukac, xlukac, 430614
 */
@Controller
public class BookController {

    @Autowired
    public PrintedBookService pbookService;

    @Autowired
    public BookService bookService;

    @RequestMapping(value = "/book/addformular", method = RequestMethod.GET)
    public ModelAndView addformular() {
        BookDTO book = new BookDTO();
        book.setDepartment(Department.Sport);
        return new ModelAndView("addbook", "book", book);
    }

    @RequestMapping(value = "/book/addpost", method = RequestMethod.POST)
    public String addpost(@ModelAttribute("book") @Valid BookDTO book, BindingResult bindingResult, ModelMap model,
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
        } catch (DuplicationException e) {
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

    @RequestMapping(value = "/book/showbooks", method = RequestMethod.GET)
    public String showbooks(ModelMap model) {

        List<BookDTO> list = bookService.findAllBooks();
        model.addAttribute("list", list);

        return "showbooks";
    }

    @RequestMapping("/book/id/{number}")
    public String showbook(ModelMap model, @PathVariable("number") int number) {
        BookDTO book = bookService.findBookById(number);
        List<PrintedBookDTO> list = pbookService.findPrintedBooksByBook(book);
        model.addAttribute("book", book);
        model.addAttribute("list", list);

        return "showbook";
    }

    @RequestMapping("/book/edit/{number}")
    public String editbook(ModelMap model, @PathVariable("number") int number) {
        BookDTO book = bookService.findBookById(number);
        model.addAttribute("book", book);

        return "editbook";
    }

    @RequestMapping("/book/delete/{number}")
    public String deletepost(ModelMap model, @PathVariable("number") int number) {
        bookService.deleteBook(bookService.findBookById(number));
        List<BookDTO> list = bookService.findAllBooks();
        model.addAttribute("list", list);

        return "redirect:/book/showbooks";
    }

    @RequestMapping(value = "/book/editpost", method = RequestMethod.POST)
    public String editpost(@ModelAttribute("pa165") @Valid BookDTO book, BindingResult bindingResult, ModelMap model,
                           RedirectAttributes redirectAttributes) {
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
        } catch (DuplicationException e) {
            redirectAttributes.addFlashAttribute("name", book.getName());
            redirectAttributes.addFlashAttribute("isbn", book.getISBN());
            redirectAttributes.addFlashAttribute("authors", book.getAuthors());
            redirectAttributes.addFlashAttribute("description", book.getDescription());
            redirectAttributes.addFlashAttribute("error", "duplicate");
            return "redirect:/book/edit";
        }
        List<BookDTO> list = bookService.findAllBooks();
        model.addAttribute("list", list);

        return "redirect:/book/id/" + String.valueOf(book.getIdBook());
    }

    @RequestMapping("/book/findbooks")
    public ModelAndView findbooks(ModelMap model) {
        ModelAndView mav = new ModelAndView("findbooks");

        mav.addObject("search", new SearchModel());

        return mav;
    }

    @RequestMapping(value = "/book/findbooks/result")
    private ModelAndView processSearch(@ModelAttribute SearchModel search) {
        ModelAndView mav = new ModelAndView("findbooks");
        if (search.getSearch() == null) {
            mav.addObject("search", new SearchModel());
            mav.addObject("list", new ArrayList<BookDTO>());
            return mav;
        }
        mav.addObject("search", search);

        if (search.getSearch().equals("ISBN")) {
            mav.addObject("list", bookService.findBooksByISBN(search.getInput()));
        } else if (search.getSearch().equals("Name")) {
            mav.addObject("list", bookService.findBooksByName(search.getInput()));
        } else if (search.getSearch().equals("Authors")) {
            mav.addObject("list", bookService.findBooksByAuthor(search.getInput()));
        } else if (search.getSearch().equals("Department")) {
            try {
                mav.addObject("list", bookService.findBooksByDepartment(Department.valueOf(search.getInput())));
            } catch (IllegalArgumentException ex) {
                mav.addObject("list", new ArrayList());
            }
        }

        return mav;
    }
}
