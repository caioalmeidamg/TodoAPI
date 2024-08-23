package br.com.TodoAPI.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.TodoAPI.Model.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}