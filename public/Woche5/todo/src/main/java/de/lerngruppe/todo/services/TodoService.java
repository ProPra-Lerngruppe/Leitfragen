package de.lerngruppe.todo.services;

import de.lerngruppe.todo.db.TodoItemRepositoryImpl;
import de.lerngruppe.todo.db.TodoListRepositoryImpl;
import de.lerngruppe.todo.domain.TodoItem;
import de.lerngruppe.todo.domain.TodoList;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    TodoItemRepositoryImpl todoItemRepository;
    TodoListRepositoryImpl todoListRepository;

    public TodoService(TodoItemRepositoryImpl todoItemRepository, TodoListRepositoryImpl todoListRepository) {
        this.todoItemRepository = todoItemRepository;
        this.todoListRepository = todoListRepository;
    }

    public List<TodoList> getAllLists(){
        return todoListRepository.getAllLists();
    }

    public void toggleState(Long itemId){
        boolean isRootTodo = todoListRepository.isRootTodo(itemId);
        if(isRootTodo) {
            TodoList list = todoListRepository.getTodoListByRootId(itemId);
            if (list != null) {
                if (!list.areSubtasksCompleted()) {
                    return;
                }
            }else{
                return;
            }
        }
        todoItemRepository.toggleState(itemId);
    }

    public void deleteTodo(Long itemId){
        boolean isRootTodo = todoListRepository.isRootTodo(itemId);
        if (isRootTodo){
            TodoList list = todoListRepository.getTodoListByRootId(itemId);
            list.getSubtasks().forEach(t-> todoItemRepository.delete(t.getId()));
            todoItemRepository.delete(list.getRoot().getId());
            todoListRepository.deleteList(list.getId());
            System.out.println("Deleting list");
        }else{
            todoListRepository.deleteListReference(itemId);
            todoItemRepository.delete(itemId);
            System.out.println("Deleting single item");
        }
    }

    public void createTodo(Long listId, String description){
        TodoItem todoItem = new TodoItem(null, description, false);
        TodoItem save = todoItemRepository.save(todoItem);
        todoListRepository.addItemToList(listId, save);
    }

    public void createTodoList(String description){
        TodoItem todoItem = new TodoItem(null, description, false);
        TodoItem save = todoItemRepository.save(todoItem);
        TodoList todoList = new TodoList(null, save, null);
        todoListRepository.saveList(todoList);
    }
}
