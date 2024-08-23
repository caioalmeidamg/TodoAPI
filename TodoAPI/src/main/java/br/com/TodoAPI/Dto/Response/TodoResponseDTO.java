package br.com.TodoAPI.Dto.Response;

import br.com.TodoAPI.Model.Todo;

public record TodoResponseDTO(
        Long id,
        String title,
        Boolean done
) {
    public TodoResponseDTO(Todo todo) {
        this(todo.getId(), todo.getTitle(), todo.getDone());
    }
}
