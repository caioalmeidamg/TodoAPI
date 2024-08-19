package br.com.TodoAPI.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.TodoAPI.Modal.Todo;

public interface TodoRepository extends JpaRepository<Todo, Integer> {
   
}