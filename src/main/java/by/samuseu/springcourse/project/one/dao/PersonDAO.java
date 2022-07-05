package by.samuseu.springcourse.project.one.dao;

import by.samuseu.springcourse.project.one.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import by.samuseu.springcourse.project.one.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE person_id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }

    public Optional<Person> show(String personName) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE person_name=?", new Object[]{personName}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny();
    }

    public List<Book> showPersonBooks(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE person_id=?", new BeanPropertyRowMapper<>(Book.class), id);

    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO Person (person_name, person_year_of_birth) VALUES(?, ?)", person.getPersonName(), person.getPersonYearOfBirth());
    }

    public void update(int person_id, Person updatedPerson) {
        jdbcTemplate.update("UPDATE Person SET person_name=?, person_year_of_birth=? WHERE person_id=?", updatedPerson.getPersonName(),
                updatedPerson.getPersonYearOfBirth(), person_id);
    }

    public void delete(int personId) {
        jdbcTemplate.update("DELETE FROM Person WHERE person_id=?", personId);
    }
}
