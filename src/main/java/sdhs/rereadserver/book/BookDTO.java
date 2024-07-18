package sdhs.rereadserver.book;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Builder
@Getter
@Setter
public class BookDTO {
    private Long id;
    private String no;
    private String name;
    private String author;
    private String publisher;
    private String publicationDate;
    private String callNumber;
    private Date registerDate;
}
