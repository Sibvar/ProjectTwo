package by.samuseu.springcourse.project.two.repositories;

import by.samuseu.springcourse.project.two.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {
    Optional<Person> findPersonByPersonName(String personName);
}
