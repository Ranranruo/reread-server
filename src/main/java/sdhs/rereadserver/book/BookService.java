package sdhs.rereadserver.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;
    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    public Optional<Book> findById(Long id) {return bookRepository.findById(id);}
    public ArrayList<Book> findAll(){
        return (ArrayList<Book>) bookRepository.findAll();
    }
    public boolean viewCountUp(Book book){
        book.setView(book.getView()+1);
        bookRepository.save(book);
        return true;
    }

}
