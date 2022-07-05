package by.samuseu.springcourse.project.one.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Person {
    private int personId;

    @NotEmpty(message = "Укажите ФИО")
    @Size(min = 2, max = 30, message = "ФИО должно быть от 2 до 30 символов")
    @Pattern(regexp = "[А-Я][а-я]+\\s[А-Я][а-я]+\\s[А-Я][а-я]+$", message = "ФИО в формате: Иванов Иван Иванович")
    private String personName;

    @Min(value = 1900, message = "Год рождения должен быть больше 1900 года")
    private int personYearOfBirth;

    public Book getPersonBooks() {
        return personBooks;
    }

    public void setPersonBooks(Book personBooks) {
        this.personBooks = personBooks;
    }

    private Book personBooks;

    public Person() {

    }

    public Person(int personId, String personName, int personYearOfBirth) {
        this.personId = personId;
        this.personName = personName;
        this.personYearOfBirth = personYearOfBirth;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public int getPersonYearOfBirth() {
        return personYearOfBirth;
    }

    public void setPersonYearOfBirth(int personYearOfBirth) {
        this.personYearOfBirth = personYearOfBirth;
    }
}