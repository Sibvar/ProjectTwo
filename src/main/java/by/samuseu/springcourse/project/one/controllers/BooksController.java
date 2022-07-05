package by.samuseu.springcourse.project.one.controllers;

import by.samuseu.springcourse.project.one.dao.BookDAO;
import by.samuseu.springcourse.project.one.models.Book;
import by.samuseu.springcourse.project.one.models.Person;
import by.samuseu.springcourse.project.one.util.BookValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BooksController {

    private final BookDAO bookDAO;
    private final BookValidator bookValidator;

    public BooksController(BookDAO bookDAO, BookValidator bookValidator) {
        this.bookDAO = bookDAO;
        this.bookValidator = bookValidator;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("books", bookDAO.index());
        return "books/index";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult) {
        bookValidator.validate(book, bindingResult);
        if (bindingResult.hasErrors())
            return "books/new";
        bookDAO.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("selectedPerson") Person person) {
        model.addAttribute("book", bookDAO.show(id));
        model.addAttribute("personBook", bookDAO.showPerson(id));
        model.addAttribute("allPeople", bookDAO.showAllPeople());
        return "books/show";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookDAO.show(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        bookValidator.validate(book, bindingResult);
        if (bindingResult.hasErrors()) {
            return "books/edit";
        }
        bookDAO.update(id, book);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/add")
    public String assignBook(@PathVariable("id") int bookId, @ModelAttribute("selectedPerson") Person person) {
        bookDAO.assignBook(bookId, person.getPersonId());
        return "redirect:/books/{id}";
    }

    @PostMapping("/{id}")
    public String releaseBook(@PathVariable("id") int id) {
        bookDAO.releaseBook(id);
        return "redirect:/books/{id}";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookDAO.delete(id);
        return "redirect:/books";
    }
}