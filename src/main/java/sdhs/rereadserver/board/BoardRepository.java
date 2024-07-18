package sdhs.rereadserver.board;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface BoardRepository extends JpaRepository<Board, Long> {
    //@Query("")
    //ArrayList<Board> getBoardPageWithCommentCount(Integer limit, Integer offset);
}