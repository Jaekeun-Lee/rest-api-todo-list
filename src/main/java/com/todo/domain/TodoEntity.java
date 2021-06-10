package com.todo.domain;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class TodoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(name = "todoOrder", nullable = false)
    private Long order;

    @Column(nullable = false)
    private Boolean completed;

    @Builder
    public TodoEntity(String title, Long order, Boolean completed) {
        this.title = title;
        this.order = order;
        this.completed = completed;
    }
}
