package ru.expogroup.HT5.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ValueGenerationType;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "Issues")
@NoArgsConstructor
public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "id_reader")
    private long idReader;
    @Column(name = "id_book")
    private long idBook;
    @Column(name = "issued_at")
    private LocalDateTime issued_at;
    @Column(name = "returned_at")
    private LocalDateTime returned_at;

    public Issue(long idReader, long idBook){
        this.idReader = idReader;
        this.idBook = idBook;
        issued_at = LocalDateTime.now();
        returned_at = null;
    }
}
