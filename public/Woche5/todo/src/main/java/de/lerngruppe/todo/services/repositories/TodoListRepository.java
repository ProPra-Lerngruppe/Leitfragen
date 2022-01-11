package de.lerngruppe.todo.services.repositories;


import de.lerngruppe.todo.domain.TodoItem;
import de.lerngruppe.todo.domain.TodoList;

import java.util.List;

public interface TodoListRepository {

    List<TodoList> getAllLists();

    TodoList getListById(Long id);

    void saveList(TodoList list);

    void addItemToList(Long listId, TodoItem item);

    void removeItemFromList(Long listId, TodoItem item);

    boolean isRootTodo(Long listId);

    Long getTodoListIdByRootId(Long rootId);

    TodoList getTodoListByRootId(Long rootId);

    void deleteList(Long listId);

    void deleteListReference(Long itemId);
}
