package sdhs.rereadserver.board;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sdhs.rereadserver.lib.ResponseFormat;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    @PostMapping("/board")
    public ResponseEntity<Map<String, Object>> addBoard(@RequestBody Map<String, Object> body){
        ResponseEntity<Map<String, Object>> response = null;
        Date now = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
        Board board = new Board();
        board.setAuthor(body.get("author").toString());
        board.setTitle(body.get("title").toString());
        board.setCreateDate(simpleDateFormat.format(now));
        board.setCategory(body.get("category").toString());
        board.setContent(body.get("content").toString());
        board.setView(0L);
        boardService.saveBoard(board);
        response = new ResponseFormat<Object>()
                .code(200)
                .message("OK")
                .data(null)
                .build();
        return response;
    }
    @GetMapping("/board")
    public ResponseEntity<Map<String, Object>> getBoard(){
        ResponseEntity<Map<String, Object>> response = null;
        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("빌려줘요", null);
        data.put("빌려드려요", null);
        data.put("찾아줘요", null);
        Optional<ArrayList<Board>> category1 = boardService.findAllByCategory("빌려줘요");
        Optional<ArrayList<Board>> category2 = boardService.findAllByCategory("빌려드려요");
        Optional<ArrayList<Board>> category3 = boardService.findAllByCategory("찾아줘요");

        if(category1.isPresent())
            data.put("빌려줘요", category1.get());
        if(category2.isPresent())
            data.put("빌려드려요", category2.get());
        if(category3.isPresent())
            data.put("찾아줘요", category3.get());
        response = new ResponseFormat<Map<String, Object>>()
                .code(200)
                .message("OK")
                .data(data)
                .build();
        return response;
    }
    @GetMapping("/board/{idx}")
    public ResponseEntity<Map<String, Object>> getBoardByIdx(@PathVariable(name = "idx") Long id){
        Optional<Board> board = boardService.findById(id);
        if(board.isEmpty()){
            ResponseEntity<Map<String, Object>> response = new ResponseFormat<Object>()
                    .code(404)
                    .message("NOT FOUND")
                    .data(null)
                    .build();
            return response;
        }
        return new ResponseFormat<Map<String, Object>>()
                .code(200)
                .message("OK")
                .data(board.get())j
                .build();
    }

}
