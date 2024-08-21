package br.com.TodoAPI.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.TodoAPI.Modal.Todo;

public interface TodoRepository extends JpaRepository<Todo, Integer> {
    Optional<Todo> findByTitle(String title);
}