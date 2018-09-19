package cs544.sample;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.validation.Valid;

@Controller
public class BookController {

    @Resource
    private IBookDao bookDao;

    @RequestMapping("/")
    public String redirectRoot() {
        return "redirect:/books";
    }

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public String getAll(Model model) {
        model.addAttribute("books", bookDao.getAll());
        return "bookList";
    }

    @RequestMapping(value = "/books/{id}", method = RequestMethod.GET)
    public String get(@PathVariable int id, Model model) {
        model.addAttribute("book", bookDao.get(id));
        return "bookDetail";
    }

    @GetMapping("/addBook")
    public String addBook(@ModelAttribute("book") Book book) {
        return "addBook";
    }


    @PostMapping(value = "/addBook")
    public String add(@Valid Book book, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            attr.addFlashAttribute("org.springframework.validation.BindingResult.contact", result);
            attr.addFlashAttribute("book", book);
            return "addBook";
        } else {
            bookDao.add(book);
            return "redirect:/books";
        }
    }

    @RequestMapping(value = "/books", method = RequestMethod.POST)
    public String add(Book book) {
        bookDao.add(book);
        return "redirect:/books";
    }

    @RequestMapping(value = "books/{id}", method = RequestMethod.POST)
    public String update(Book book, @PathVariable int id) {
        bookDao.update(id, book);
        return "redirect:/books";
    }

    @RequestMapping(value = "/books/delete", method = RequestMethod.POST)
    public String delete(int bookId) {
        bookDao.delete(bookId);
        return "redirect:/books";
    }

    @ExceptionHandler(value = NoSuchResourceException.class)
    public ModelAndView handle(Exception e) {
        ModelAndView mv = new ModelAndView();
        mv.getModel().put("e", e);
        mv.setViewName("noSuchResource");
        return mv;
    }
}
