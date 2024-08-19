package br.com.TodoAPI.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

import br.com.TodoAPI.Modal.Todo;
import br.com.TodoAPI.Repository.TodoRepository;

@Service
public class TodoService {
    
    @Autowired
    private TodoRepository repositorio;

    public Optional<Todo> getAlunos(Integer id) {
        Optional<Todo> retorno =  repositorio.findById(id);
        return retorno;
    }

    public Todo setTodo(Todo todo) {
        Todo savedTodo = repositorio.save(todo);
        return savedTodo;
    }
}
