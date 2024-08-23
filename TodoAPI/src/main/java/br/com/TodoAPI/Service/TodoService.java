package br.com.TodoAPI.Service;

import br.com.TodoAPI.Dto.Request.TodoRequestDTO;
import br.com.TodoAPI.Dto.Response.TodoResponseDTO;
import br.com.TodoAPI.Model.Todo;
import br.com.TodoAPI.Repository.TodoRepository;
import br.com.TodoAPI.util.CommonMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoService {
	
	@Autowired
	private TodoRepository todoRepository;
	
	public List<TodoResponseDTO> findAll() {
		return todoRepository.findAll().stream().map(TodoResponseDTO::new).collect(Collectors.toList());
	}
	
	public TodoResponseDTO findById(Long id) {
		return new TodoResponseDTO(CommonMethods.getEntityById(id, todoRepository));
	}
	
	public TodoResponseDTO createTodo(TodoRequestDTO todoRequestDTO) {
		
		Todo todo = new Todo(todoRequestDTO);
		
		return new TodoResponseDTO(todoRepository.save(todo));
	}
	
	public TodoResponseDTO updateTodo(Long id, TodoRequestDTO todoRequestDTO) {
		
		Todo updatedTodo = CommonMethods.getEntityById(id, todoRepository);
		
		updatedTodo.setTitle(todoRequestDTO.title());
		updatedTodo.setDone(todoRequestDTO.done());
		
		todoRepository.save(updatedTodo);
		
		return new TodoResponseDTO(updatedTodo);
	}
	
	public void delete(Long id) {
		Todo deletedTodo = CommonMethods.getEntityById(id, todoRepository);
		
		todoRepository.delete(deletedTodo);
	}
}
