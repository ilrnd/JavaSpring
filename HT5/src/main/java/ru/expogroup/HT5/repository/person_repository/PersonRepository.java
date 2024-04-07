package ru.expogroup.HT5.repository.person_repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.expogroup.HT5.entity.Persons.Person;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<Person> findByLogin(String login);

}
