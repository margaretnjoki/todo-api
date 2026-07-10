package com.margaretnjoki.todo_api.service;

import com.margaretnjoki.todo_api.dto.CreateTodoRequest;
import com.margaretnjoki.todo_api.dto.UpdateTodoRequest;
import com.margaretnjoki.todo_api.exception.TodoNotFoundException;
import com.margaretnjoki.todo_api.model.Todo;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TodoService {
    private final Map<UUID, Todo> store = new ConcurrentHashMap<>();

    public List<Todo> findAll() {
        return store.values().stream().toList();
    }

    public Todo findById(UUID id) {
       return Optional.ofNullable(store.get(id))
               .orElseThrow(() -> new TodoNotFoundException(id));
    }

    public Todo create(CreateTodoRequest request) {
        Instant now=Instant.now();
        Todo todo = Todo.builder()
                .id(UUID.randomUUID())
                .title(request.title())
                .description(request.description())
                .updatedAt(now)
                .createdAt(now)
                .build();
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

    public Todo update(UUID id, UpdateTodoRequest request){
        Todo existing= findById(id);
        if (request.title() != null) existing.setTitle(request.title());
        if (request.description() != null) existing.setDescription(request.description());
        if (request.completed() != null) existing.setDone(request.completed());
        return existing;
    }

    public List<Todo>completedTasks(){
       return store.values()
                .stream()
                .filter(Todo::isDone)
                .toList();
    }


}
