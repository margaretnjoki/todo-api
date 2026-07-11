package com.margaretnjoki.todo_api.Repository;

import com.margaretnjoki.todo_api.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TodoRepository  extends JpaRepository<Todo, UUID> {
}
