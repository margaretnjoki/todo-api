package com.margaretnjoki.todo_api.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateTodoRequest(
      @NotBlank(message = "title cannot be blank") String title,
      @NotBlank(message = "description  cannot be blank") String description
) {
}
