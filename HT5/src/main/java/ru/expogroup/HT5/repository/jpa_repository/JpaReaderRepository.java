package ru.expogroup.HT5.repository.jpa_repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.expogroup.HT5.entity.Reader;

@Repository
public interface JpaReaderRepository extends JpaRepository<Reader, Long> {

    Reader findByName(String name);
    Reader findById(long id);
}
