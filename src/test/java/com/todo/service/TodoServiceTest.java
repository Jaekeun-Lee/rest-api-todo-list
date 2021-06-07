package com.todo.service;

import com.todo.model.TodoEntity;
import com.todo.model.TodoRequest;
import com.todo.repository.TodoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TodoServiceTest {

    @Mock
    private TodoRepository todoRepository;

    @InjectMocks
    private TodoService todoService;

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

        Optional<TodoEntity> optional = Optional.of(
                TodoEntity.builder()
                        .id(123L)
                        .title("Find By Id Test Title")
                        .build());

        given(todoRepository.findById(anyLong())).willReturn(optional);

        TodoEntity actual = todoService.findById(123L);
        TodoEntity expect = optional.get();

        assertEquals(expect.getId(), actual.getId());
        assertEquals(expect.getTitle(), actual.getTitle());
        assertEquals(expect.getOrder(), actual.getOrder());
        assertEquals(expect.getCompleted(), actual.getCompleted());

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

        TodoEntity expect = TodoEntity.builder()
                                            .id(1L)
                                            .title("Find All Test Title")
                                            .build();

        todoEntities.add(expect);

        given(todoRepository.findAll()).willReturn(todoEntities);


        TodoEntity actual = todoService.findAll().get(0);

        assertEquals(expect.getId(), actual.getId());
        assertEquals(expect.getTitle(), actual.getTitle());
        assertEquals(expect.getOrder(), actual.getOrder());
        assertEquals(expect.getCompleted(), actual.getCompleted());

    }

}