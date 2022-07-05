package by.samuseu.springcourse.project.one.dao;

import by.samuseu.springcourse.project.one.models.Book;
import by.samuseu.springcourse.project.one.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;


    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO Book (book_name, book_author, book_year) VALUES(?, ?, ?)",book.getBookName(), book.getBookAuthor(), book.getBookYear());
    }

    public Book show(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE book_id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny().orElse(null);
    }

    public Optional<Book> show(String bookName) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE book_name=?", new Object[]{bookName}, new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny();
    }

    public Person showPerson(int id) {
        return jdbcTemplate.query("SELECT * FROM Person JOIN Book ON Person.person_id = Book.person_id WHERE book_id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }

    public List<Person> showAllPeople() {
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }

    public void update(int book_id, Book updatedBook) {
        jdbcTemplate.update("UPDATE Book SET book_name=?, book_author=?, book_year=? WHERE book_id=?", updatedBook.getBookName(), updatedBook.getBookAuthor(),
                updatedBook.getBookYear(), book_id);
    }

    public void assignBook(int bookId, int personId) {
        jdbcTemplate.update("UPDATE Book SET person_id=? WHERE book_id=?",personId,  bookId);
    }

    public void releaseBook(int bookId) {
        jdbcTemplate.update("UPDATE Book SET person_id=null WHERE book_id=?", bookId);
    }

    public void delete(int bookId) {
        jdbcTemplate.update("DELETE FROM Book WHERE book_id=?", bookId);
    }
}
