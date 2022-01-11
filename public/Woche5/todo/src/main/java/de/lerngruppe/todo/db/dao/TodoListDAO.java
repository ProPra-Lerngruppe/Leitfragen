package de.lerngruppe.todo.db.dao;

import de.lerngruppe.todo.db.dto.TodoListDTO;
import de.lerngruppe.todo.domain.TodoList;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface TodoListDAO extends CrudRepository<TodoListDTO, Long> {

    @Query ("""
SELECT root FROM todo.todo_list_dto WHERE root = :listId;
""")
    Long getRootIdByTodoListDTOId(@Param("listId") Long listId);

    @Query ("""
SELECT id FROM todo.todo_list_dto WHERE root = :root;
""")
    Long getTodoListIdByRootId(@Param("root") Long root);

    @Query ("""
SELECT * FROM todo.todo_list_dto WHERE root = :root;
""")
    TodoListDTO getTodoListByRootId(@Param("root") Long root);

    @Query ("""
SELECT todo_list_dto FROM todo.todo_item_reference_dto WHERE item = :id;
""")
    Long getTodoListIdBySubtaskId(@Param("id") Long id);
}
