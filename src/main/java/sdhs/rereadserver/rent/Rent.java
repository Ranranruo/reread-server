package sdhs.rereadserver.rent;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

@Entity
@ToString
@Getter
@Setter
public class Rent {
    @Column(name = "idx")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "student")
    private String student;

    @Column(name = "return_date")
    private Date returnDate;

    @Column(name = "state")
    private String state;

    @Column(name = "book_no")
    private String bookNo;
}
