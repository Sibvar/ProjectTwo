package by.samuseu.springcourse.project.two.controllers;

import by.samuseu.springcourse.project.two.models.Book;
import by.samuseu.springcourse.project.two.models.Person;
import by.samuseu.springcourse.project.two.services.BooksService;
import by.samuseu.springcourse.project.two.services.PeopleService;
import by.samuseu.springcourse.project.two.util.BookValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BooksController {

    private final BooksService booksService;
    private final PeopleService peopleService;
    private final BookValidator bookValidator;

    @Autowired
    public BooksController(BooksService booksService, PeopleService peopleService, BookValidator bookValidator) {
        this.booksService = booksService;
        this.peopleService = peopleService;
        this.bookValidator = bookValidator;
    }

    @GetMapping()
    public String index(Model model, @RequestParam(required = false) boolean sort_by_year,
                        @RequestParam(required = false) Integer page, @RequestParam(required = false) Integer books_per_page) {
        if (sort_by_year) {
            model.addAttribute("books", booksService.sortBooksByYear(sort_by_year));
        } else if (page != null && books_per_page != null) {
            model.addAttribute("books", booksService.pagesOfBooks(page, books_per_page));
            model.addAttribute("books", booksService.sortPagesOfBooks(sort_by_year, page, books_per_page));
        } else {
            model.addAttribute("books", booksService.findAll());
        }
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
        booksService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("selectedPerson") Person person) {
        model.addAttribute("book", booksService.findOne(id));
        model.addAttribute("personBook", booksService.findOne(id).getOwner());
        model.addAttribute("allPeople", peopleService.findAll());
        return "books/show";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", booksService.findOne(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        bookValidator.validate(book, bindingResult);
        if (bindingResult.hasErrors()) {
            return "books/edit";
        }
        booksService.update(id, book);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/add")
    public String assignBook(@PathVariable("id") int bookId, @ModelAttribute("selectedPerson") Person person) {
        booksService.assignBook(bookId, person);
        System.out.println(person.getPersonId());
        return "redirect:/books/{id}";
    }

    @PostMapping("/{id}")
    public String releaseBook(@PathVariable("id") int id) {
        booksService.releaseBook(id);
        return "redirect:/books/{id}";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        booksService.delete(id);
        return "redirect:/books";
    }
}