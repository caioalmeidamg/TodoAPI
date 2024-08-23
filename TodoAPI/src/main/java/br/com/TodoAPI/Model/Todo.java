package br.com.TodoAPI.Model;

import br.com.TodoAPI.Dto.Request.TodoRequestDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
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

    private Boolean done;

    public Todo(TodoRequestDTO todoRequestDTO) {
        this.title = todoRequestDTO.title();
        this.done = todoRequestDTO.done();
    }
}