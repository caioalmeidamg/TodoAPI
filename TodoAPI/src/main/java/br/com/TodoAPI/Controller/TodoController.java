package br.com.TodoAPI.Controller;

import java.util.Optional;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.websocket.server.PathParam;
import br.com.TodoAPI.Modal.Todo;
import br.com.TodoAPI.Repository.TodoRepository;
import br.com.TodoAPI.Service.TodoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/api/todos")
public class TodoController {
    
    @Autowired
    private TodoService service;

    @GetMapping("")
    public Optional<List<Todo>> geTodo() {
        return service.get();
    }

    @GetMapping("/{id}")
    public Optional<Todo> getId(@PathVariable(value  = "id") Integer id) {     
        return service.getId(id);
    }

    @PostMapping
    public ResponseEntity<Todo> postTodo(@RequestBody Todo todo) {
        Todo savedTodo = service.post(todo);
        return new ResponseEntity<>(savedTodo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public Optional<Todo> updateTodo(@PathVariable(value  = "id") Integer id, @RequestBody Todo corpoRequest) {
        return service.update(id,corpoRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Integer id) {
        return service.delete(id);
    }

}
