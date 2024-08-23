package br.com.TodoAPI.Dto.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TodoRequestDTO(

        @NotBlank(message = "Title is required")
        String title,

        @NotNull(message = "You need to inform if the task is done or not")
        Boolean done
) {
}
