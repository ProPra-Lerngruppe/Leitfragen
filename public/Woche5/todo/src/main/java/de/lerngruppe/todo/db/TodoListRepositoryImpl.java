package de.lerngruppe.todo.db;

import de.lerngruppe.todo.db.dto.TodoListDTO;
import de.lerngruppe.todo.db.dao.TodoListDAO;
import de.lerngruppe.todo.domain.TodoList;
import de.lerngruppe.todo.services.repositories.TodoListRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TodoListRepositoryImpl implements TodoListRepository {

    TodoListDAO todoListDAO;

    public TodoListRepositoryImpl(TodoListDAO todoListDAO) {
        this.todoListDAO = todoListDAO;
    }

    @Override
    public List<TodoList> getAllLists() {
        List<TodoListDTO> todoListDTO = todoListDAO.findAll();
        return todoListDTO.stream().map(TodoListDTO::convertDTOToList).toList();
    }

    @Override
    public TodoList getListById(Long id) {
        TodoListDTO listDTO = todoListDAO.findById(id).orElse(null);
        if (listDTO != null) return listDTO.convertDTOToList();
        else return null;
    }

    public void toggleState(Long listId, int list_key){
        TodoListDTO todoListDTO = todoListDAO.findById(listId).orElse(null);
        System.out.println(todoListDTO);
        if (todoListDTO != null){
            TodoList todoList = todoListDTO.convertDTOToList();
            if (todoList != null) {
                if(list_key == -1){
                    todoList.toggleMainTask();
                }else{
                    todoList.toggleTodoItem(list_key);
                }
                saveList(todoList);
            }
        }
    }

    @Override
    public void saveList(TodoList list) {
        TodoListDTO todoListDTO = TodoListDTO.convertToDTO(list);
        todoListDAO.save(todoListDTO);
    }

    @Override
    public void addItemToList(Long listId, String beschreibung, boolean completed) {
        TodoListDTO todoListDTO = todoListDAO.findById(listId).orElse(null);
        System.out.println(todoListDTO);
        if (todoListDTO != null){
            TodoList todoList = todoListDTO.convertDTOToList();
            if (todoList != null) {
                todoList.addItemToList(beschreibung, completed);
                saveList(todoList);
            }
        }
    }

    @Override
    public void removeItemFromList(Long listId, String beschreibung, boolean completed) {
        TodoListDTO todoListDTO = todoListDAO.findById(listId).orElse(null);
        if (todoListDTO != null){
            TodoList todoList = todoListDTO.convertDTOToList();
            try {
                if (todoList != null) {
                    todoList.removeItemToList(beschreibung, completed);
                    saveList(todoList);
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public void deleteList(Long listId) {
        TodoListDTO todoListDTO = todoListDAO.findById(listId).orElse(null);
        if (todoListDTO != null){
            TodoList todoList = todoListDTO.convertDTOToList();
            if (todoList != null) {
                todoListDAO.delete(todoListDTO);
            }
        }
    }
}
