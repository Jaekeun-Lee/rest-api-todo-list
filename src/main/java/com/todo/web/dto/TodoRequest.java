package com.todo.web.dto;

import com.todo.domain.TodoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoRequest {

    private String title;
    private Long order ;
    private Boolean completed;

    public TodoEntity toEntity() {
        return TodoEntity.builder()
                .title(title)
                .order(order)
                .completed(completed)
                .build();
    }
}
