package by.samuseu.springcourse.project.two.repositories;

import by.samuseu.springcourse.project.two.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {
}
