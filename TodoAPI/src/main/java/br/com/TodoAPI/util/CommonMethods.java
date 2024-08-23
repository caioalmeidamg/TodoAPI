package br.com.TodoAPI.util;

import br.com.TodoAPI.exception.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

public class CommonMethods {

    public static <T> T getEntityById(Long id, JpaRepository<T, Long> repository) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity not found with id: " + id));
    }
}
