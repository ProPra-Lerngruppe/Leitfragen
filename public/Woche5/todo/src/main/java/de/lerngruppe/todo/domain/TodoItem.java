package de.lerngruppe.todo.domain;

import de.lerngruppe.todo.stereotypes.ValueObject;

@ValueObject
record TodoItem(String description, Boolean completed) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TodoItem todoItem = (TodoItem) o;

        if (!description.equals(todoItem.description)) return false;
        return completed.equals(todoItem.completed);
    }

    @Override
    public int hashCode() {
        int result = description.hashCode();
        result = 31 * result + completed.hashCode();
        return result;
    }
}
