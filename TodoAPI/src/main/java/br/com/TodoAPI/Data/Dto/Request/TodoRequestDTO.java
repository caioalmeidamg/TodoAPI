package br.com.TodoAPI.Data.Dto.Request;

import jakarta.validation.constraints.NotBlank;

public record TodoRequestDTO(

        @NotBlank(message = "Title is required")
        String title,

        @NotBlank(message = "You need to inform if the task is done or not")
        boolean done
) {
}
