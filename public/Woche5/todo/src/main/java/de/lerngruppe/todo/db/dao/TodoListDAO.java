package de.lerngruppe.todo.db.dao;

import de.lerngruppe.todo.db.dto.TodoListDTO;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TodoListDAO extends CrudRepository<TodoListDTO, Long> {

    List<TodoListDTO> findAll();
}
