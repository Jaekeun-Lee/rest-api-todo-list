package com.todo.controller;

import com.todo.model.TodoEntity;
import com.todo.model.TodoRequest;
import com.todo.model.TodoResponse;
import com.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/")
public class TodoController {

    private final TodoService todoService;

    @PostMapping
    public ResponseEntity<TodoResponse> add(@RequestBody TodoRequest request) {
        if (ObjectUtils.isEmpty(request.getTitle()))
            return ResponseEntity.badRequest().build();

        if (ObjectUtils.isEmpty(request.getOrder()))
            request.setOrder(0L);

        if (ObjectUtils.isEmpty(request.getCompleted()))
            request.setCompleted(false);
        return ResponseEntity.ok(new TodoResponse(todoService.add(request)));
    }

    @GetMapping("{id}")
    public ResponseEntity<TodoResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(new TodoResponse(todoService.findById(id)));
    }

    @GetMapping
    public ResponseEntity<List<TodoResponse>> findAll() {
        List<TodoEntity> todoEntityList = todoService.findAll();

        return ResponseEntity.ok(todoEntityList.stream().map(TodoResponse::new).collect(Collectors.toList()));
    }

    @PatchMapping("{id}")
    public ResponseEntity<TodoResponse> updateById(@PathVariable Long id, @RequestBody TodoRequest request) {
        return ResponseEntity.ok(new TodoResponse(todoService.updateById(id, request)));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        return todoService.deleteById(id) == 1 ?
                ResponseEntity.ok().build() : ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAll() {
        todoService.deleteAll();
        return ResponseEntity.ok().build();
    }
}
