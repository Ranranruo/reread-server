package sdhs.rereadserver.book;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

@Entity
@ToString
@Getter
@Setter
public class Book {
    @Column(name = "idx")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "no")
    private String no;

    @Column(name = "name")
    private String name;

    @Column(name = "author")
    private String author;

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "publication_date")
    private String publicationDate;

    @Column(name = "call_number")
    private String callNumber;

    @Column(name = "register_date")
    private Date registerDate;

    @Column(name = "storage")
    private String storage;

    @Column(name = "view")
    private Long view;

}
