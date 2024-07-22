package sdhs.rereadserver.lib;

import org.springframework.http.ResponseEntity;

import java.util.LinkedHashMap;
import java.util.Map;

public class ResponseFormat<T>{
    private Map<String, Object> response = new LinkedHashMap<String, Object>();
    private Integer code;
    private String message;
    public T data;

    public ResponseFormat code(Integer code){
        this.code=code;
        return this;
    }
    public ResponseFormat message(String message){
        this.message=message;
        return this;
    }
    public ResponseFormat data(T data){
        this.data=data;
        return this;
    }
    public ResponseEntity<Map<String, Object>> build(){
        response.put("code", code);
        response.put("message", message);
        response.put("data", data);
        return ResponseEntity.status(code).body(response);
    }
}
