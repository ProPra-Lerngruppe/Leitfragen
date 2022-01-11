package de.lerngruppe.todo.db.dto;

import de.lerngruppe.todo.domain.TodoItem;
import de.lerngruppe.todo.domain.TodoList;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

public record TodoListDTO(
        @Id Long id,
        Long root,
        List<TodoItemReferenceDTO> todoItems
) {
    public TodoList convertDTOToList(){
        if (this.root == null) return null;
        TodoList todoList = new TodoList(this.id, null, null);
        todoList.setRoot(new TodoItem(this.root, null, null));
        if (todoList.getSubtasks() == null){
            todoList.setSubtasks(new ArrayList<>());
        }
        this.todoItems.forEach(t -> todoList.getSubtasks().add(new TodoItem(t.item(), null, false)));
        return todoList;
    }

    public static TodoListDTO convertToDTO(TodoList list){
        return new TodoListDTO(list.getId(), list.getRoot().getId(), list.getSubtasks() == null ? null : list.getSubtasks().stream().map(t -> new TodoItemReferenceDTO(TodoItemDTO.convertToDTO(t).id())).toList());
    }
}
