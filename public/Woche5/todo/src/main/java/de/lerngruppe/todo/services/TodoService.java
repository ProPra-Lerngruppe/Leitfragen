package de.lerngruppe.todo.services;

import de.lerngruppe.todo.db.TodoListRepositoryImpl;
import de.lerngruppe.todo.domain.TodoList;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    TodoListRepositoryImpl todoListRepository;

    public TodoService( TodoListRepositoryImpl todoListRepository) {
        this.todoListRepository = todoListRepository;
    }

    public List<TodoList> getAllLists(){
        return todoListRepository.getAllLists();
    }

    public void toggleState(Long list, int todo_key){
        todoListRepository.toggleState(list, todo_key);
    }

    public void deleteTodo(Long listId, int list_key) {
        TodoList list = todoListRepository.getListById(listId);
        if (list != null) {
            if (list_key == -1) {
                todoListRepository.deleteList(list.getId());
                System.out.println("Deleting list");
            } else {
                list.getSubtasks().remove(list_key);
                todoListRepository.saveList(list);
                System.out.println("Deleting single item");
            }
        }else{
            System.out.println("No list found with this index");
        }
    }

    public void createTodo(Long listId, String description){
        todoListRepository.addItemToList(listId, description, false);
    }

    public void createTodoList(String description){
        TodoList todoList = new TodoList(null);
        todoList.replaceMainTask(description, false);
        todoListRepository.saveList(todoList);
    }
}
