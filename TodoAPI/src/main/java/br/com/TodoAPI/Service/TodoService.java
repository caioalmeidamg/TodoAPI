package br.com.TodoAPI.Service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.TodoAPI.Modal.Todo;
import br.com.TodoAPI.Repository.TodoRepository;

@Service
public class TodoService {
    
    @Autowired
    private TodoRepository repositorio;

    public Optional<List<Todo>> get() {
        List<Todo> todos = repositorio.findAll();
        Optional<List<Todo>> optionalTodos = todos.isEmpty() ? Optional.empty() : Optional.of(todos);
        return optionalTodos;        
    }

    public Optional<Todo> getId(Integer id) {
        Optional<Todo> retorno =  repositorio.findById(id);
        return retorno;
    }

    public Todo post(Todo todo) {
        Todo savedTodo = repositorio.save(todo);
        return savedTodo;
    }

    public Optional<Todo> update(Integer id, Todo requisicao){
        Optional<Todo> retorno =  repositorio.findById(id);

        if(retorno.isPresent()){
            Todo valor = retorno.get();

            if (requisicao.getTitle() != null) {
                valor.setTitle(requisicao.getTitle());
            }
            if (requisicao.getDone() != null) {
                valor.setDone(requisicao.getDone());
            }

            return Optional.of(repositorio.save(valor));
        }
        return Optional.empty();
    }

    
    public ResponseEntity<Void> delete(Integer id) {

        if (repositorio.existsById(id)) {

            repositorio.deleteById(id);
            return ResponseEntity.noContent().build();

        } else {
            return ResponseEntity.notFound().build();

        }
    }
}
