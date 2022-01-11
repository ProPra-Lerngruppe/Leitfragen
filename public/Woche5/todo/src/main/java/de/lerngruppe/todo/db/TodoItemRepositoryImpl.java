package de.lerngruppe.todo.db;

import de.lerngruppe.todo.db.dao.TodoItemDAO;
import de.lerngruppe.todo.db.dto.TodoItemDTO;
import de.lerngruppe.todo.domain.TodoItem;
import de.lerngruppe.todo.services.repositories.TodoItemRepository;
import de.lerngruppe.todo.services.repositories.TodoListRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class TodoItemRepositoryImpl implements TodoItemRepository {

    TodoItemDAO todoItemDAO;

    public TodoItemRepositoryImpl(TodoItemDAO todoItemDAO) {
        this.todoItemDAO = todoItemDAO;
    }


    @Override
    public TodoItem findById(Long id) {
        TodoItemDTO todoItemDTO = todoItemDAO.findById(id).orElse(null);
        if (todoItemDTO == null) return null;
        else return todoItemDTO.convertToItem();
    }

    @Override
    public void toggleState(Long id) {
        TodoItem todoItem = findById(id);
        todoItem.setCompleted(!todoItem.isCompleted());
        save(todoItem);
    }

    @Override
    public TodoItem save(TodoItem todoItem) {
        TodoItemDTO dto = TodoItemDTO.convertToDTO(todoItem);
        TodoItemDTO save = todoItemDAO.save(dto);
        return save.convertToItem();
    }

    @Override
    public void delete(Long id) {
        todoItemDAO.delete(TodoItemDTO.convertToDTO(findById(id)));
    }

    @Override
    public void updateDescription(Long id, String description) {
        TodoItem todoItem = findById(id);
        todoItem.setDescription(description);
        save(todoItem);
    }
}
