package com.margaretnjoki.todo_api.service;

import com.margaretnjoki.todo_api.exception.TodoNotFoundException;
import com.margaretnjoki.todo_api.model.Todo;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;

@Service
public class TodoService {
    private final Map<UUID, Todo> store = new HashMap<>();

    public List<Todo> findAll() {
        return new ArrayList<>(store.values());
    }

    public Todo findById(UUID id) {
        Todo todo = store.get(id);
        if (todo == null) {
            throw new TodoNotFoundException(id);
        }
        return todo;
    }

    public Todo create(String title) {
        Todo todo = new Todo(UUID.randomUUID(), title, false, Instant.now());
        store.put(todo.getId(), todo);
        return todo;
    }

    public Todo markDone(UUID id) {
        Todo todo = findById(id);
        todo.setDone(true);
        return todo;
    }

    public void delete(UUID id) {
        if (!store.containsKey(id)) {
            throw new TodoNotFoundException(id);
        }
        store.remove(id);
    }

    public Todo updateTitle(UUID id, String title) {
        Todo todo = store.get(id);
        if (todo == null) {
            throw new NoSuchElementException("Todo not found");
        }
        todo.setTitle(title);
        return todo;
    }
}
