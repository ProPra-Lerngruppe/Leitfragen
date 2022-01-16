package de.lerngruppe.todo.services.repositories;


import de.lerngruppe.todo.domain.TodoList;

import java.util.List;

public interface TodoListRepository {

    List<TodoList> getAllLists();

    TodoList getListById(Long id);

    void saveList(TodoList list);

    void addItemToList(Long listId, String beschreibung, boolean completed);

    void removeItemFromList(Long listId, String beschreibung, boolean completed);

    void deleteList(Long listId);

}
