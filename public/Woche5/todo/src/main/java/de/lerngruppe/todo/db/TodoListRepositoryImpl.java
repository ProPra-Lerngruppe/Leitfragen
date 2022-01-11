package de.lerngruppe.todo.db;

import de.lerngruppe.todo.db.dao.TodoItemDAO;
import de.lerngruppe.todo.db.dao.TodoListDAO;
import de.lerngruppe.todo.db.dto.TodoListDTO;
import de.lerngruppe.todo.domain.TodoItem;
import de.lerngruppe.todo.domain.TodoList;
import de.lerngruppe.todo.services.repositories.TodoListRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.StreamSupport;

@Repository
public class TodoListRepositoryImpl implements TodoListRepository {

    TodoListDAO todoListDAO;
    TodoItemDAO todoItemDAO;

    public TodoListRepositoryImpl(TodoListDAO todoListDAO, TodoItemDAO todoItemDAO) {
        this.todoListDAO = todoListDAO;
        this.todoItemDAO = todoItemDAO;
    }

    @Override
    public List<TodoList> getAllLists() {
        List<TodoListDTO> todoListDTO = StreamSupport.stream(todoListDAO.findAll().spliterator(), false).toList();
        List<TodoList> todoLists = todoListDTO.stream().map(TodoListDTO::convertDTOToList).toList();
        for (TodoList tl : todoLists){
            loadTodoItemValues(tl);
        }
        return todoLists;
    }

    @Override
    public TodoList getListById(Long id) {
        TodoListDTO listDTO = todoListDAO.findById(id).orElse(null);
        if (listDTO == null && listDTO.root() == null) return null;
        else {
            TodoList tl = listDTO.convertDTOToList();
            loadTodoItemValues(tl);
            return tl;
        }
    }

    private void loadTodoItemValues(TodoList tl) {
        TodoItem rootTodo = todoItemDAO.findById(tl.getRoot().getId()).orElse(null).convertToItem();
        tl.setRoot(rootTodo);
        List<TodoItem> list = tl.getSubtasks().stream().map(t-> todoItemDAO.findById(t.getId()).orElse(null).convertToItem()).toList();
        tl.setSubtasks(list);
    }

    @Override
    public void saveList(TodoList list) {
        TodoListDTO todoListDTO = TodoListDTO.convertToDTO(list);
        todoListDAO.save(todoListDTO);
    }

    @Override
    public void addItemToList(Long listId, TodoItem item) {
        TodoListDTO todoListDTO = todoListDAO.findById(listId).orElse(null);
        System.out.println(todoListDTO);
        if (todoListDTO != null){
            TodoList todoList = todoListDTO.convertDTOToList();
            if (todoList != null) {
                todoList.getSubtasks().add(item);
                saveList(todoList);
            }
        }
    }

    @Override
    public void removeItemFromList(Long listId, TodoItem item) {
        TodoListDTO todoListDTO = todoListDAO.findById(listId).orElse(null);
        if (todoListDTO != null){
            TodoList todoList = todoListDTO.convertDTOToList();
            try {
                if (todoList != null) {
                    todoList.getSubtasks().remove(item);
                    saveList(todoList);
                }
            }catch (Exception e){
                System.out.println(e);
            }
        }
    }

    @Override
    public boolean isRootTodo(Long itemId) {
        Long rootTodoItemId = todoListDAO.getRootIdByTodoListDTOId(itemId);
        return rootTodoItemId != null;
    }

    @Override
    public Long getTodoListIdByRootId(Long rootId) {
        return todoListDAO.getTodoListIdByRootId(rootId);
    }

    @Override
    public TodoList getTodoListByRootId(Long rootId) {
        TodoListDTO todoListDTO = todoListDAO.getTodoListByRootId(rootId);
        return todoListDTO.convertDTOToList();
    }

    @Override
    public void deleteList(Long listId) {
        TodoListDTO todoListDTO = todoListDAO.findById(listId).orElse(null);
        if (todoListDTO != null){
            TodoList todoList = todoListDTO.convertDTOToList();
            if (todoList != null) {
                todoList.getSubtasks().forEach(t -> todoItemDAO.deleteById(t.getId()));
                todoItemDAO.deleteById(todoList.getRoot().getId());
                todoListDAO.delete(todoListDTO);
            }
        }
    }

    @Override
    public void deleteListReference(Long itemId) {
        Long listId = todoListDAO.getTodoListIdBySubtaskId(itemId);
        if (listId != null){
            TodoListDTO todoListDTO = todoListDAO.findById(listId).orElse(null);
            if (todoListDTO != null){
                TodoList todoList = todoListDTO.convertDTOToList();
                todoList.getSubtasks().remove(new TodoItem(itemId, null, null));
                todoListDAO.save(TodoListDTO.convertToDTO(todoList));
            }
        }
    }
}
