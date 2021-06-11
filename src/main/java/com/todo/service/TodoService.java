package com.todo.service;

import com.todo.domain.TodoEntity;
import com.todo.web.dto.TodoRequest;
import com.todo.domain.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    /**
     * Todo 아이템 추가
     * @param request 추가될 Todo 아이템 요청
     * @return 추가된 Todo 엔티티
     */
    public TodoEntity add(TodoRequest request) {
        return todoRepository.save(request.toEntity());
    }


    /**
     * 특정 Todo 아이템 조회
     * @param id 조회랑 아이템 아이디
     * @return 조회된 Todo 엔티티
     *          해당 아이디가 존재하지 않을 경우 ResponseStatusException 발생
     */
    public TodoEntity findById(Long id) {
        return todoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }


    /**
     * 전체 Todo 아이템 목록 조회
     * @return 전체 Todo 엔티티 목록
     */
    public List<TodoEntity> findAll() {
        return todoRepository.findAll();
    }


    /**
     * Todo 아이템 수정
     * @param id 수정할 Todo 아이템 아이디
     * @param request 수정할 내용
     * @return 수정된 Todo 엔티티
     */
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


    /**
     * 특정 Todo 아이템 삭제
     * @param id 삭제할 Todo 아이템 아이디
     */
    public int deleteById(Long id) {
        todoRepository.deleteById(id);
        return todoRepository.findById(id).isEmpty() ? 1 : 0;
    }


    /**
     * 전체 Todo 아이템 목록 삭제
     */
    public void deleteAll() {
        todoRepository.deleteAll();
    }
}
