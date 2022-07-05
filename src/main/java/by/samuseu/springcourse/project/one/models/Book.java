package by.samuseu.springcourse.project.one.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Book {

    private int bookId;
    @NotEmpty(message = "Укажите название книги")
    @Size(min = 2, max = 40, message = "Название должно быть от 2 до 40 символов")
    private String bookName;
    @NotEmpty(message = "Укажите автора книги")
    @Pattern(regexp = "[А-Я][а-я]+\\s[А-Я][а-я]+$", message = "Автор в формате: Иван Иванов")
    private String bookAuthor;
    @Min(value = 1, message = "Укажите год издания книги")
    private int bookYear;

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public int getBookYear() {
        return bookYear;
    }

    public void setBookYear(int bookYear) {
        this.bookYear = bookYear;
    }
}