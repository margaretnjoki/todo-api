package com.margaretnjoki.todo_api.controller;

import com.margaretnjoki.todo_api.dto.CreateTodoRequest;
import com.margaretnjoki.todo_api.dto.TodoResponse;
import com.margaretnjoki.todo_api.dto.TodoStatsResponse;
import com.margaretnjoki.todo_api.dto.UpdateTodoRequest;
import com.margaretnjoki.todo_api.service.TodoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/todos")
@Tag(name = "Todos", description = "Manage todo items")
public class TodoController {
    private final TodoService service;

    public TodoController(TodoService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "list all todos")
    public List<TodoResponse> all() {
        return service.findAll().stream().map(TodoResponse::from).toList();
    }

    @GetMapping("/{id}")
    @Operation(summary = "get a todo by id")
    public TodoResponse one(@PathVariable UUID id) {
        return TodoResponse.from(service.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "create a todo task")
    public TodoResponse create(@Valid @RequestBody CreateTodoRequest request) {
        return TodoResponse.from(service.create(request));
    }

    @PutMapping("/{id}/done")
    @Operation(summary = "update a todo by id to make sure its done")
    public TodoResponse markDone(@PathVariable UUID id) {
        return TodoResponse.from(service.markDone(id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete a todo by id")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
/*
    @PutMapping("/{id}/{title}")
    @Operation(summary = "update the title of the todo by id ")
    public Todo updateTitle(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateTodoRequest request) {

        return service.updateTitle(id, request.title());
    }
 */

    @PutMapping("/{id}")
    public TodoResponse update(@PathVariable UUID id, @Valid @RequestBody UpdateTodoRequest request) {
        return TodoResponse.from(service.update(id, request));
    }

    @GetMapping("/completed")
    @Operation(summary = "List all completed todos")
    public List<TodoResponse> completed() {
        return service.completedTasks()
                .stream()
                .map(TodoResponse::from)
                .toList();
    }
    @GetMapping("/stats")
    public TodoStatsResponse allAndCompleted(){
        return service.count();
    }
}



