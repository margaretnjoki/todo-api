package com.margaretnjoki.todo_api.service;

import com.margaretnjoki.todo_api.Repository.TodoRepository;
import com.margaretnjoki.todo_api.dto.CreateTodoRequest;
import com.margaretnjoki.todo_api.dto.TodoStatsResponse;
import com.margaretnjoki.todo_api.dto.UpdateTodoRequest;
import com.margaretnjoki.todo_api.exception.TodoNotFoundException;
import com.margaretnjoki.todo_api.model.Todo;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;

@Service
public class TodoService {
    private final TodoRepository repository;

    public TodoService(TodoRepository repository) {
        this.repository = repository;
    }

    public List<Todo> findAll() {
        return repository.findAll();
    }

    public Todo findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new TodoNotFoundException(id));
    }

    public Todo create(CreateTodoRequest request) {
        Instant now = Instant.now();
        Todo todo = Todo.builder()
                .title(request.title())
                .description(request.description())
                .updatedAt(now)
                .createdAt(now)
                .build();
        return repository.save(todo);
    }

    public Todo markDone(UUID id) {
        Todo todo = findById(id);
        todo.setDone(true);
        return repository.save(todo);
    }

    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new TodoNotFoundException(id);
        }
        repository.deleteById(id);
    }

    /*public Todo updateTitle(UUID id, String title) {
        Todo todo = store.get(id);
        if (todo == null) {
            throw new NoSuchElementException("Todo not found");
        }
        todo.setTitle(title);
        return todo;
    }
     */

    public Todo update(UUID id, UpdateTodoRequest request) {
        Todo existing = findById(id);
        if (request.title() != null) existing.setTitle(request.title());
        if (request.description() != null) existing.setDescription(request.description());
        if (request.completed() != null) existing.setDone(request.completed());
        return repository.save(existing);
    }

    public List<Todo> completedTasks() {
        return repository.findAll()
                .stream()
                .filter(Todo::isDone)
                .toList();
    }

    public TodoStatsResponse count() {
        long total = repository.count();
        long done = completedTasks().size();
        return new TodoStatsResponse(total, done);
    }

}
