package by.samuseu.springcourse.project.two.repositories;

import by.samuseu.springcourse.project.two.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {
    List<Book> findBookByBookName(String bookName);
    List<Book> findBookByBookNameStartsWith(String bookName);
}
