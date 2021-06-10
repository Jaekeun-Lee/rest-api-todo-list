package com.todo.web.dto;

import com.todo.domain.TodoEntity;
import lombok.Data;

@Data
public class TodoResponse {

    private Long id;
    private String title;
    private Long order;
    private Boolean completed;
    private String url;

    public TodoResponse(TodoEntity todoEntity) {
        this.id = todoEntity.getId();
        this.title = todoEntity.getTitle();
        this.order = todoEntity.getOrder();
        this.completed = todoEntity.getCompleted();
        this.url = "http://localhost:8080/" + this.id;
    }

}
