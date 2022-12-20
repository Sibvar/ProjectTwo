package by.samuseu.springcourse.project.two.util;

import by.samuseu.springcourse.project.two.models.Book;
import by.samuseu.springcourse.project.two.services.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class BookValidator implements Validator {

    private final BooksService booksService;

    @Autowired
    public BookValidator(BooksService booksService) {
        this.booksService = booksService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Book.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Book book = (Book) target;

        if (booksService.findBookByBookName(book.getBookName()).isPresent()) {
            errors.rejectValue("bookName", "", "Книга с таким названием уже есть в базе");
        }
    }
}
