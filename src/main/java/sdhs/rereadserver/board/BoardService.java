package sdhs.rereadserver.board;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public Optional<ArrayList<Board>> findAllByCategory(String category){
        return boardRepository.findAllByCategory(category);
    }
    public boolean saveBoard(Board board){
        boardRepository.save(board);
        return true;
    }
    public Optional<Board> findById(Long id){
        return boardRepository.findById(id);
    }
}
