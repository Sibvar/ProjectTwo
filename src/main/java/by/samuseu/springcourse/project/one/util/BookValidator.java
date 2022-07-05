package by.samuseu.springcourse.project.one.util;

import by.samuseu.springcourse.project.one.dao.BookDAO;
import by.samuseu.springcourse.project.one.models.Book;
import by.samuseu.springcourse.project.one.models.Person;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class BookValidator implements Validator {

    private final BookDAO bookDAO;

    public BookValidator(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Book.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Book book = (Book) target;

        if (bookDAO.show(book.getBookName()).isPresent()) {
            errors.rejectValue("bookName", "", "Книга с таким названием уже есть в базе");
        }
    }
}
