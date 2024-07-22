package sdhs.rereadserver.rent;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Builder
@Getter
@Setter
public class RentDTO {
    private String student;
    private Date returnDate;
    private String state;
    private String bookNo;
}
