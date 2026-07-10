package com.margaretnjoki.todo_api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Todo {
    private UUID id;
    private String title;
    private boolean done;
    private Instant createdAt;
    private Instant updatedAt;
    private String description;
}