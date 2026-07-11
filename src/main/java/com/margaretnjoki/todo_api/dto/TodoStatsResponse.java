package com.margaretnjoki.todo_api.dto;

public record TodoStatsResponse(
        long total,
        long done
) {
}
