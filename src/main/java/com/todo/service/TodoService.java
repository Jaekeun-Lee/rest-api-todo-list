package com.todo.service;

import com.todo.model.TodoEntity;
import com.todo.model.TodoRequest;
import com.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoEntity add(TodoRequest request) {
        return todoRepository.save(request.toEntity());
    }

    public TodoEntity findById(Long id) {
        return todoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public List<TodoEntity> findAll() {
        return todoRepository.findAll();
    }

    public TodoEntity updateById(Long id, TodoRequest request) {
        TodoEntity todoEntity = this.findById(id);
        if (request.getTitle() != null) {
            todoEntity.setTitle(request.getTitle());
        }
        if (request.getOrder() != null) {
            todoEntity.setOrder(request.getOrder());
        }
        if (request.getCompleted() != null) {
            todoEntity.setCompleted(request.getCompleted());
        }

        return todoRepository.save(todoEntity);


    }

    public int deleteById(Long id) {
        todoRepository.deleteById(id);
        return todoRepository.findById(id).isEmpty() ? 1 : 0;
    }

    public void deleteAll() {
        todoRepository.deleteAll();
    }
}
