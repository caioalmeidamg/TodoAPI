package br.com.TodoAPI.Controller;

import java.util.List;

import br.com.TodoAPI.Dto.Request.TodoRequestDTO;
import br.com.TodoAPI.Dto.Response.TodoResponseDTO;
import br.com.TodoAPI.util.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.TodoAPI.Service.TodoService;


@RestController
@RequestMapping("/api/todos")
@Tag(name = "Todo", description = "Endpoints relacionados a tarefas")
public class TodoController {
    
    @Autowired
    private TodoService todoService;

    @Operation(summary = "Cria uma nova tarefa",
            description = "Cria uma nova tarefa com base nos dados informados",
            tags = {"Todo"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "201",
                            content = @Content(schema = @Schema(implementation = TodoResponseDTO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    @PostMapping(produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public ResponseEntity<TodoResponseDTO> create(@Valid @RequestBody TodoRequestDTO todoRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(todoService.createTodo(todoRequestDTO));
    }


    @Operation(summary = "Lista todas as tarefas",
            description = "Retorna uma lista com todas as tarefas cadastradas",
            tags = {"Todo"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = TodoResponseDTO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    @GetMapping(produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<List<TodoResponseDTO>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(todoService.findAll());
    }

    @Operation(summary = "Busca uma tarefa por ID",
            description = "Retorna uma tarefa com base no ID informado",
            tags = {"Todo"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = TodoResponseDTO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<TodoResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(todoService.findById(id));
    }

    @Operation(summary = "Atualiza uma tarefa",
            description = "Atualiza uma tarefa com base no ID informado e nos dados informados",
            tags = {"Todo"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = TodoResponseDTO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public ResponseEntity<TodoResponseDTO> update(@PathVariable Long id, @Valid @RequestBody TodoRequestDTO todoRequestDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(todoService.updateTodo(id, todoRequestDTO));
    }

    @Operation(summary = "Deleta uma tarefa",
            description = "Deleta uma tarefa com base no ID informado",
            tags = {"Todo"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "204",
                            content = @Content(schema = @Schema(implementation = Void.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        todoService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }



}
