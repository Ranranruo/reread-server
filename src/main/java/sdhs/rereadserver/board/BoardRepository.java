package sdhs.rereadserver.board;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {
    @Query(value = "SELECT * FROM board WHERE category = :category ORDER BY create_date DESC", nativeQuery = true)
    Optional<ArrayList<Board>> findAllByCategory(@Param("category") String category);
}