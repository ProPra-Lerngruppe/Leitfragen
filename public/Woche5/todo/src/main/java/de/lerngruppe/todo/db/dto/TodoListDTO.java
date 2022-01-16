package de.lerngruppe.todo.db.dto;

import de.lerngruppe.todo.domain.TodoList;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Embedded;

import java.util.ArrayList;
import java.util.List;

public record TodoListDTO(
        @Id Long id,
        @Embedded(onEmpty = Embedded.OnEmpty.USE_NULL)
        TodoItemDTO root,
        List<TodoItemDTO> todoItems
) {
    public TodoList convertDTOToList(){
        if (this.root == null) return null;
        TodoList todoList = new TodoList(this.id);
        todoList.replaceMainTask(this.root.description(), this.root.completed());
        this.todoItems.forEach(t -> todoList.addItemToList(t.description(), t.completed()));
        return todoList;
    }

    public static TodoListDTO convertToDTO(TodoList list){
        final List<TodoItemDTO> todoItemDTOS = new ArrayList<>();
        if (list.getSubtasks() != null) {
            for (int i = 0; i < list.getSubtasks().size(); i++) {
                todoItemDTOS.add(new TodoItemDTO(list.getDescriptionOfItem(i), list.getCompletedOfItem(i)));
            }
        }
        return new TodoListDTO(list.getId(), new TodoItemDTO(list.getMainTaskDescription(), list.isMainTaskCompleted()), todoItemDTOS);
    }
}
