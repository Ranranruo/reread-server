package sdhs.rereadserver.Book;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public ArrayList<Book> getBooks(){
        ArrayList<Book> list = bookService.findAll();
        for(Book book : list){
            System.out.println(book.toString());
        }
        return bookService.findAll();
    }
}
