package com.todo.web;

import com.todo.domain.TodoEntity;
import com.todo.web.dto.TodoRequest;
import com.todo.web.dto.TodoResponse;
import com.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/")
public class TodoController {

    private final TodoService todoService;

    @PostMapping
    public ResponseEntity<TodoResponse> add(@RequestBody TodoRequest request) {
        log.info("ADD");
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
        log.info("FIND BY ID");
        return ResponseEntity.ok(new TodoResponse(todoService.findById(id)));
    }

    @GetMapping
    public ResponseEntity<List<TodoResponse>> findAll() {
        log.info("FIND ALL");
        List<TodoEntity> todoEntityList = todoService.findAll();

        return ResponseEntity.ok(todoEntityList.stream().map(TodoResponse::new).collect(Collectors.toList()));
    }

    @PatchMapping("{id}")
    public ResponseEntity<TodoResponse> updateById(@PathVariable Long id, @RequestBody TodoRequest request) {
        log.info("UPDATE BY ID");
        return ResponseEntity.ok(new TodoResponse(todoService.updateById(id, request)));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        log.info("DELETE BY ID");
        return todoService.deleteById(id) == 1 ?
                ResponseEntity.ok().build() : ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAll() {
        log.info("DELETE ALL");
        todoService.deleteAll();
        return ResponseEntity.ok().build();
    }
}
