package com.todo.service;

import com.todo.domain.TodoEntity;
import com.todo.web.dto.TodoRequest;
import com.todo.domain.repository.TodoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TodoServiceTest {

    @InjectMocks
    private TodoService todoService;

    @Mock
    private TodoRepository todoRepository;


    @Test
    void add() {

        // todoRepository.save 호출해서 TodoEntity 값을 받으면 받은 Entity 값을 반환
        when(todoRepository.save(any(TodoEntity.class)))
                .then(AdditionalAnswers.returnsFirstArg());

        TodoRequest expected = new TodoRequest();
        expected.setTitle("Add Test Title");

        TodoEntity actual = todoService.add(expected);

        assertEquals(expected.getTitle(), actual.getTitle());

    }

    @Test
    void findById() {

        TodoEntity todo = new TodoEntity();
        todo.setTitle("test");
        todo.setId(123L);
        todo.setOrder(0L);
        todo.setCompleted(false);
        Optional<TodoEntity> expected = Optional.of(todo);

        given(this.todoRepository.findById(anyLong()))
                .willReturn(expected);

        TodoEntity actual = this.todoService.findById(123L);

        assertEquals(actual.getId(), 123L);
        assertEquals(actual.getOrder(), 0L);
        assertFalse(actual.getCompleted());
        assertEquals(actual.getTitle(), "test");

    }

    @Test
    void findByIdFailed() {

        // id 검색 실패시 에러
        given(todoRepository.findById(anyLong())).willReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> {
            todoService.findById(123L);
        });
    }

    @Test
    void findAll() {

        List<TodoEntity> todoEntities =mock(List.class);

        TodoEntity expect = new TodoEntity();
        expect.setTitle("test");
        expect.setId(123L);
        expect.setOrder(0L);
        expect.setCompleted(false);

        todoEntities.add(expect);

        given(todoRepository.findAll()).willReturn(todoEntities);


        TodoEntity actual = todoService.findAll().get(0);

        assertEquals(expect.getId(), actual.getId());
        assertEquals(expect.getTitle(), actual.getTitle());
        assertEquals(expect.getOrder(), actual.getOrder());
        assertEquals(expect.getCompleted(), actual.getCompleted());

    }

}