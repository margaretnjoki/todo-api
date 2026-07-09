package com.margaretnjoki.todo_api.controller;

import com.margaretnjoki.todo_api.dto.CreateTodoRequest;
import com.margaretnjoki.todo_api.dto.TodoResponse;
import com.margaretnjoki.todo_api.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/todos")
public class TodoController {
    private final TodoService service;

    public TodoController(TodoService service) {
        this.service = service;
    }
    @GetMapping
    public List<TodoResponse> all(){
        return service.findAll().stream().map(TodoResponse::from).toList();
    }
    @GetMapping("/{id}")
    public TodoResponse one(@PathVariable UUID id){
        return TodoResponse.from(service.findById(id));
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TodoResponse create(@Valid @RequestBody CreateTodoRequest request){
        return TodoResponse.from(service.create(request.title()));
    }
    @PutMapping("/{id}/done")
    public TodoResponse markDone(@PathVariable UUID id) {
        return TodoResponse.from(service.markDone(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}



