package sdhs.rereadserver.board;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

@Entity
@ToString
@Getter
@Setter
public class Board {
    @Column(name = "idx")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "author")
    private String author;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "view")
    private Long view;
}
