package de.lerngruppe.todo.web;

import de.lerngruppe.todo.domain.TodoList;
import de.lerngruppe.todo.services.TodoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class TodoController {

    private final TodoService service;

    public TodoController(TodoService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String index(Model m){
        List<TodoList> allLists = service.getAllLists();
        System.out.println(allLists);
        m.addAttribute("todoLists", allLists);
        return "index";
    }

    @PostMapping("/toggle-state")
    public String toggleState(Long listId, Integer list_key){
        service.toggleState(listId, list_key);
        return "redirect:/";
    }

    @PostMapping("/delete-todo")
    public String deleteTodo(Long listId, Integer list_key){
        service.deleteTodo(listId, list_key);
        return "redirect:/";
    }

    @PostMapping("/create-todo")
    public String createTodo(Long listId, String description){
        if (!description.equals("") && listId != null) service.createTodo(listId, description);
        return "redirect:/";
    }

    @PostMapping("/create-todo-list")
    public String createTodoList(String description){
        if (!description.equals("")) service.createTodoList(description);
        return "redirect:/";
    }
}
