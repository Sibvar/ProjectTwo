package by.samuseu.springcourse.project.two.services;

import by.samuseu.springcourse.project.two.models.Book;
import by.samuseu.springcourse.project.two.models.Person;
import by.samuseu.springcourse.project.two.repositories.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class BooksService {

    private final BooksRepository booksRepository;

    @Autowired
    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public List<Book> findAll() {
        return booksRepository.findAll();
    }

    public Book findOne(int id) {
        return booksRepository.findById(id).orElse(null);
    }

    public Book findByBookName(String bookName) {
        return booksRepository.findByBookName(bookName);
    }

    @Transactional
    public void assignBook(int bookId, Person owner) {
        booksRepository.findById(bookId).orElse(null).setOwner(owner);
        booksRepository.findById(bookId).orElse(null).setDateOfAssignment(new Date());
    }

//    public boolean isExpired(int bookId) {
//        Book book = booksRepository.findById(bookId).orElse(null);
//
//
//        return false;
//    }

    @Transactional
    public void releaseBook(int bookId) {
        booksRepository.findById(bookId).orElse(null).setOwner(null);
        booksRepository.findById(bookId).orElse(null).setDateOfAssignment(null);
    }

    @Transactional
    public void save(Book book) {
        booksRepository.save(book);
    }

    @Transactional
    public void update(int id, Book updatedBook) {
        updatedBook.setBookId(id);
        booksRepository.save(updatedBook);
    }

    @Transactional
    public void delete(int id) {
        booksRepository.deleteById(id);
    }
}