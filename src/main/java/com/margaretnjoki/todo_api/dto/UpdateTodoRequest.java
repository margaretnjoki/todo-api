package com.margaretnjoki.todo_api.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdateTodoRequest(
        @NotBlank String title
) {
}
