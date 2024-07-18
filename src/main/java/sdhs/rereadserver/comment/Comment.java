package sdhs.rereadserver.comment;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
public class Comment {
    @Column(name = "idx")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content")
    private String content;

    @Column(name = "author")
    private String author;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "board_idx")
    private Long boardIdx;
}
