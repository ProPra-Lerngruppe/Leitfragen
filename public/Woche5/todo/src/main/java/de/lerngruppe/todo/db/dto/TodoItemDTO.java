package de.lerngruppe.todo.db.dto;

import de.lerngruppe.todo.domain.TodoItem;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;

public record TodoItemDTO(
        @Id Long id,
        @NotEmpty @Max(200) String description,
        boolean completed){

    public TodoItem convertToItem(){
        return new TodoItem(this.id, this.description, this.completed);
    }

    public static TodoItemDTO convertToDTO(TodoItem item){
        return new TodoItemDTO(item.getId(), item.getDescription(), item.isCompleted());
    }
}
