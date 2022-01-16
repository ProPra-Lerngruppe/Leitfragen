package de.lerngruppe.todo.domain;

import de.lerngruppe.todo.stereotypes.AggregateRoot;

import java.util.ArrayList;
import java.util.List;

@AggregateRoot
public class TodoList {

    private Long id = null;
    private TodoItem mainTask = null;
    private final List<TodoItem> subtasks = new ArrayList<>();

    public TodoList(Long id) {
        this.id = id;
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

    public String getMainTaskDescription(){
        return mainTask.description();
    }

    public boolean isMainTaskCompleted(){
        return mainTask.completed();
    }

    public String getDescriptionOfItem(int index){
        return subtasks.get(index).description();
    }

    public boolean getCompletedOfItem(int index){
        return subtasks.get(index).completed();
    }

    public void toggleMainTask() {
        if (areSubtasksCompleted()){
            String mainTaskDescription = getMainTaskDescription();
            boolean isRootCompleted = isMainTaskCompleted();
            replaceMainTask(mainTaskDescription, !isRootCompleted);
        }
    }

    public TodoItem getMainTask() {
        return mainTask;
    }

    public void replaceMainTask(String beschreibung, boolean completed){
        this.mainTask = new TodoItem(beschreibung, completed);
    }

    private boolean areSubtasksCompleted(){
        long count = subtasks.stream().filter(TodoItem::completed).count();
        return count == subtasks.size();
    }

    public void toggleTodoItem(int listKey){
        TodoItem todoItem = this.getSubtasks().get(listKey);
        boolean completedTodo = todoItem.completed();
        this.getSubtasks().set(listKey, new TodoItem(todoItem.description(), !completedTodo));
        if (completedTodo && this.mainTask.completed()){
            String mainTaskDescription = getMainTaskDescription();
            replaceMainTask(mainTaskDescription, false);
        }
    }

    public void addItemToList(String beschreibung, boolean completed){
        this.subtasks.add(new TodoItem(beschreibung, completed));
    }

    public void removeItemToList(String beschreibung, boolean completed){
        TodoItem todoItem = new TodoItem(beschreibung, completed);
        this.subtasks.remove(todoItem);
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
