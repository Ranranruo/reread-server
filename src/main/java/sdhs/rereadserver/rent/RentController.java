package sdhs.rereadserver.rent;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sdhs.rereadserver.book.BookDTO;
import sdhs.rereadserver.lib.ResponseFormat;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequiredArgsConstructor
public class RentController {
    private final RentService rentService;
    @PostMapping("/rent")
    public ResponseEntity<Map<String, Object>> addRent(@RequestBody Map<String, Object> body){
        System.out.println("connect!");
        System.out.println(body.toString());
        ResponseEntity<Map<String, Object>> response;
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 3);
        Date date = new Date(cal.getTimeInMillis());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Rent rent = new Rent();
        rent.setBookNo(body.get("bookNo").toString());
        rent.setStudent((String) body.get("student"));
        rent.setReturnDate(java.sql.Date.valueOf(simpleDateFormat.format(date)));
        rent.setState("대여중");
        rentService.saveRent(rent);
        response = new ResponseFormat<Object>()
                .code(200)
                .message("OK")
                .data(null)
                .build();
        return response;
    }
    @PutMapping("/rent/return")
    public ResponseEntity<Map<String, Object>> returnBook(@RequestBody Map<String, Object> body){
        ResponseEntity<Map<String, Object>> response;
        Long id = Long.valueOf(body.get("idx").toString());
        Optional<Rent> data = rentService.findById(id);
        if(data.isEmpty()){
            response = new ResponseFormat<Object>()
                    .code(404)
                    .message("NOT FOUND")
                    .data(null)
                    .build();
            return response;
        }
        Rent rent = data.get();
        if(rent.getState().equals("반납 완료")){
            response = new ResponseFormat<Object>()
                    .code(202)
                    .message("already returned")
                    .data(null)
                    .build();
            return response;
        }
        rent.setState("반납 완료");
        rentService.saveRent(rent);
        response = new ResponseFormat<Object>()
                .code(200)
                .message("OK")
                .data(null)
                .build();
        return response;
    }
    @PutMapping("/rent")
    public ResponseEntity<Map<String, Object>> rentDateUpdate(@RequestBody Map<String, Object> body){
        ResponseEntity<Map<String, Object>> response;
        int plusDay = (int) body.get("plusDay");
        Long id = Long.valueOf(body.get("idx").toString());
        Optional<Rent> data = rentService.findById(id);
        if(data.isEmpty()){
            response = new ResponseFormat<Object>()
                    .code(404)
                    .message("NOT FOUND")
                    .data(null)
                    .build();
            return response;
        }
        Rent rent = data.get();
        if(rent.getState().equals("반납 완료")){
            response = new ResponseFormat<Object>()
                    .code(202)
                    .message("already returned")
                    .data(null)
                    .build();
            return response;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = rent.getReturnDate();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, plusDay);
        Date newDate = new Date(cal.getTimeInMillis());
        rent.setReturnDate(java.sql.Date.valueOf(simpleDateFormat.format(newDate)));
        rentService.saveRent(rent);
        response = new ResponseFormat<Object>()
                .code(200)
                .message("OK")
                .data(null)
                .build();
        return response;
    }
    @GetMapping("/rent/{no}")
    public ResponseEntity<Map<String, Object>> findRent(@PathVariable(value = "no") String no){
        ResponseEntity<Map<String, Object>> response;
        Rent rent;
        Optional<Rent> data = rentService.findByBookNo(no);
        if(data.isEmpty()){
            response = new ResponseFormat<Object>()
                    .code(404)
                    .message("NOT FOUND")
                    .data(null)
                    .build();
            return response;
        }
        rent = data.get();
        Date now = new Date();
        Date returnDate = rent.getReturnDate();
        if(now.after(returnDate)){ rent.setState("반납일 초과"); }
        if(now.equals(returnDate)){ rent.setState("반납일"); }

        response = new ResponseFormat<Object>()
                .code(200)
                .message("OK")
                .data(rent)
                .build();
        return response;
    }
}
