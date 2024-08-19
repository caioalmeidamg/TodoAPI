package br.com.TodoAPI.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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


@RestController
@RequestMapping("/api/todos")
public class TodoController {
    
    @Autowired
    private TodoService service;

    @GetMapping("/{id}")
    public Optional<Todo> getTodo( @PathVariable(value  = "id") Integer id) {     
        return service.getAlunos(id);
    }

    @PostMapping
    public ResponseEntity<Todo> createTodo(@RequestBody Todo todo) {
        Todo savedTodo = service.setTodo(todo);
        return new ResponseEntity<>(savedTodo, HttpStatus.CREATED);
    }
    
}
