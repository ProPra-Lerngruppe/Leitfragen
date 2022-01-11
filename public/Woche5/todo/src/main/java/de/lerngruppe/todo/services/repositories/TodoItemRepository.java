package de.lerngruppe.todo.services.repositories;


import de.lerngruppe.todo.db.dto.TodoItemDTO;
import de.lerngruppe.todo.domain.TodoItem;

public interface TodoItemRepository {

    TodoItem findById(Long id);

    void toggleState(Long id);

    TodoItem save(TodoItem todoItem);

    void delete(Long id);

    void updateDescription(Long id, String description);

}
