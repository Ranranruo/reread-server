package sdhs.rereadserver.rent;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sdhs.rereadserver.book.BookDTO;
import sdhs.rereadserver.lib.ResponseFormat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class RentController {
    private final RentService rentService;
    @PostMapping("/rent")
    public ResponseEntity<Map<String, Object>> addRent(@RequestBody Map<String, Object> body){
        System.out.println(body.toString());
        ResponseEntity<Map<String, Object>> response;
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 3);
        Date date = new Date(cal.getTimeInMillis());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Rent rent = new Rent();
        rent.setBookIdx(Long.valueOf(body.get("bookIdx").toString()));
        rent.setStudent((String) body.get("student"));
        rent.setReturnDate(java.sql.Date.valueOf(simpleDateFormat.format(date)));
        rentService.saveRent(rent);
        response = new ResponseFormat<ArrayList<Object>>()
                .code(200)
                .message("OK")
                .data(null)
                .build();
        return response;
    }
}
