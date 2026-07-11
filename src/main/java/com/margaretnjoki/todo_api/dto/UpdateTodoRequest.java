package com.margaretnjoki.todo_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateTodoRequest(
        @Size(max = 200)  String title,
        @Size(max = 2000) String description,
        Boolean completed
) {
}
