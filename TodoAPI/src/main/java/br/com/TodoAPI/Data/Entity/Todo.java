package br.com.TodoAPI.Data.Entity;

import br.com.TodoAPI.Data.Dto.Request.TodoRequestDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "task")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private boolean done;

    public Todo(TodoRequestDTO todoRequestDTO) {
        this.title = todoRequestDTO.title();
        this.done = todoRequestDTO.done();
    }
}