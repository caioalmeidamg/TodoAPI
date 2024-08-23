package br.com.TodoAPI.IntegrationTest;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import br.com.TodoAPI.IntegrationTest.Config.PostgresqlTestContainerConfig;
import br.com.TodoAPI.Model.Todo;
import br.com.TodoAPI.Repository.TodoRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;


@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(OrderAnnotation.class)
public class TodoRepositoryTest extends PostgresqlTestContainerConfig {

    @Autowired
    private TodoRepository todoRepository;

    private static Todo todo1;

    private static Todo todo2;

    @BeforeAll
    public static void setUp() {

        todo1 = new Todo();
        todo1.setTitle("Teste 1");
        todo1.setDone(false);

        todo2 = new Todo();
        todo2.setTitle("Teste 2");
        todo2.setDone(true);
    }

    @Test
    @Order(1)
    public void testSave() {

        //save todo1
        Todo savedTodo = todoRepository.save(todo1);
        assertNotNull(savedTodo);
        assertNotNull(savedTodo.getId());
        assertEquals(todo1.getTitle(), savedTodo.getTitle());
        assertEquals(todo1.getDone(), savedTodo.getDone());
    }

    @Test
    @Order(2)
    public void testFindById() {

        Todo savedTodo = todoRepository.save(todo1);

        Todo foundTodo = todoRepository.findById(savedTodo.getId()).orElse(null);
        assertNotNull(foundTodo);
        assertEquals(savedTodo.getId(), foundTodo.getId());
        assertEquals(savedTodo.getTitle(), foundTodo.getTitle());
        assertEquals(savedTodo.getDone(), foundTodo.getDone());
    }

    @Test
    @Order(3)
    public void testFindAll() {

        todoRepository.save(todo1);
        todoRepository.save(todo2);

        List<Todo> todos = todoRepository.findAll();
        assertNotNull(todos);

        assertTrue(todos.size() == 2);

        assertEquals(todo1.getTitle(), todos.get(0).getTitle());
        assertEquals(todo1.getDone(), todos.get(0).getDone());

        assertEquals(todo2.getTitle(), todos.get(1).getTitle());
        assertEquals(todo2.getDone(), todos.get(1).getDone());
    }

    @Test
    @Order(4)
    public void testUpdate() {

        Todo savedTodo = todoRepository.save(todo1);

        savedTodo.setTitle("Teste 1 - Atualizado");
        savedTodo.setDone(true);

        Todo updatedTodo = todoRepository.save(savedTodo);

        assertEquals(savedTodo.getId(), updatedTodo.getId());
        assertEquals(savedTodo.getTitle(), updatedTodo.getTitle());
        assertEquals(savedTodo.getDone(), updatedTodo.getDone());
    }

    @Test
    @Order(5)
    public void testDelete() {
        Todo savedTodo = todoRepository.save(todo1);
        todoRepository.delete(savedTodo);

        assertFalse(todoRepository.findById(savedTodo.getId()).isPresent());
    }


}
