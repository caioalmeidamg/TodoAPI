package br.com.TodoAPI.UnitTest.Service;

import br.com.TodoAPI.Dto.Request.TodoRequestDTO;
import br.com.TodoAPI.Dto.Response.TodoResponseDTO;
import br.com.TodoAPI.Model.Todo;
import br.com.TodoAPI.Repository.TodoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TodoServiceTest {
	@InjectMocks
	br.com.TodoAPI.Service.TodoService taskService;
	
	@Mock
	TodoRepository repository;
	
	@Test
	void findAllTasks() {
		List<Todo> taskList = new ArrayList<>();
		taskList.add(new Todo(1L, "Tarefa1", false));
		taskList.add(new Todo(2L, "Tarefa2", false));
		taskList.add(new Todo(3L, "Tarefa3", false));
		taskList.add(new Todo(4L, "Tarefa4", false));
		taskList.add(new Todo(5L, "Tarefa5", false));
		taskList.add(new Todo(6L, "Tarefa6", false));
		taskList.add(new Todo(7L, "Tarefa7", false));
		taskList.add(new Todo(8L, "Tarefa8", false));
		taskList.add(new Todo(9L, "Tarefa9", false));
		taskList.add(new Todo(10L, "Tarefa10", false));
		
		when(repository.findAll()).thenReturn(taskList);
		// Call the service method
		List<TodoResponseDTO> taskServiceList = taskService.findAll();
		
		// Verify the output
		assertEquals(taskList.size(), taskServiceList.size());
		
		for (int i = 0; i < taskServiceList.size(); i++) {
			assertEquals(taskServiceList.get(i), new TodoResponseDTO(taskList.get(i)));
		}
	}
	
	@Test
	void findTaskById() {
		Todo mockById = new Todo(1L, "Tarefa1", false);
		when(repository.findById(1L)).thenReturn(Optional.of(mockById));
		
		// Call the service method
		TodoResponseDTO task = taskService.findById(1L);
		
		// Verify the output
		assertEquals(task, new TodoResponseDTO(mockById));
	}
	
	@Test
	void createTask() {
		Todo mockById = new Todo(1L, "Tarefa1", false);
		when(repository.save(any(Todo.class))).thenReturn(mockById);
		
		// Call the service method
		TodoResponseDTO task = taskService.createTodo(new TodoRequestDTO("Tarefa1", false));
		
		// Verify the output
		TodoResponseDTO expectedTask = new TodoResponseDTO(mockById);
		assertEquals(expectedTask, task);
	}
	
	@Test
	void updateTask() {
		Todo mockById = new Todo(1L, "Tarefa1", false);
		when(repository.findById(1L)).thenReturn(Optional.of(mockById));
		
		// Call the service method
		TodoResponseDTO task = taskService.updateTodo(1L, new TodoRequestDTO("Tarefa1", true));
		
		// Verify the output
		assertEquals(task, new TodoResponseDTO(mockById));
	}
	
	@Test
	void deleteTask() {
		Todo mockById = new Todo(1L, "Tarefa1", false);
		when(repository.findById(1L)).thenReturn(Optional.of(mockById));
		
		// Call the service method
		taskService.delete(1L);
		
		// Verify the output
		assertEquals(taskService.findAll().size(), 0);
	}
	
}
