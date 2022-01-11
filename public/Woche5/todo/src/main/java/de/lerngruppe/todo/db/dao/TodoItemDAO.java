package de.lerngruppe.todo.db.dao;

import de.lerngruppe.todo.db.dto.TodoItemDTO;
import org.springframework.data.repository.CrudRepository;

public interface TodoItemDAO extends CrudRepository<TodoItemDTO, Long> {
}
