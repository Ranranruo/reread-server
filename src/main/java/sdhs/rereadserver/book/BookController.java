package sdhs.rereadserver.book;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import sdhs.rereadserver.lib.ResponseFormat;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping("/book/{idx}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable(value = "idx") Long id){
        ResponseEntity<Map<String, Object>> response;
        Optional<Book> data = bookService.findById(id);
        BookDTO bookDto;
        if(data.isEmpty()){
            response = new ResponseFormat<Object>()
                    .code(404)
                    .message("NOT FOUND")
                    .data(null)
                    .build();
            return response;
        }
        Book book = data.get();
        bookService.viewCountUp(book);
        bookDto = BookDTO.builder()
                .id(book.getId())
                .no(book.getNo())
                .name(book.getName())
                .author(book.getAuthor())
                .publisher(book.getPublisher())
                .publicationDate(book.getPublicationDate())
                .callNumber(book.getCallNumber())
                .registerDate(book.getRegisterDate())
                .build();
        response = new ResponseFormat<Book>()
                .code(200)
                .message("OK")
                .data(book)
                .build();
        return response;
    }

    @GetMapping("/book")
    public ResponseEntity<Map<String, Object>> findAll(){
        ResponseEntity<Map<String, Object>> response;
        ArrayList<Book> bookList = bookService.findAll();
        ArrayList<BookDTO> data = new ArrayList<BookDTO>();
        for(Book book : bookList){
            BookDTO bookDto = BookDTO.builder()
                    .id(book.getId())
                    .no(book.getNo())
                    .name(book.getName())
                    .author(book.getAuthor())
                    .publisher(book.getPublisher())
                    .publicationDate(book.getPublicationDate())
                    .callNumber(book.getCallNumber())
                    .registerDate(book.getRegisterDate())
                    .build();
            data.add(bookDto);
        }
        response = new ResponseFormat<ArrayList<BookDTO>>()
                .code(200)
                .message("OK")
                .data(data)
                .build();
        return response;
    }
}
