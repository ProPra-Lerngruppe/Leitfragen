package de.lerngruppe.todo.domain;

import java.util.List;

public class TodoList {

    Long id;
    TodoItem root;
    List<TodoItem> subtasks;

    public TodoList(Long id, TodoItem root, List<TodoItem> subtasks) {
        this.id = id;
        this.root = root;
        this.subtasks = subtasks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<TodoItem> getSubtasks() {
        return subtasks;
    }

    public void setSubtasks(List<TodoItem> subtasks) {
        this.subtasks = subtasks;
    }

    public TodoItem getRoot() {
        return root;
    }

    public void setRoot(TodoItem root) {
        this.root = root;
    }

    public boolean areSubtasksCompleted(){
        long count = subtasks.stream().filter(TodoItem::isCompleted).count();
        return count == subtasks.size();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
