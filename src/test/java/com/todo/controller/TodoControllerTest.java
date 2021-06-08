package com.todo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.todo.model.TodoEntity;
import com.todo.model.TodoRequest;
import com.todo.service.TodoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TodoController.class)
class TodoControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    TodoService todoService;

    private TodoEntity expect;

    @BeforeEach
    void setup() {

        //테스트 시작 전 초기 데이터 초기화 생성
        this.expect = TodoEntity.builder()
                                    .id(1L)
                                    .title("Test Title")
                                    .build();
    }
    @Test
    void add() throws Exception{
        when(todoService.add(any(TodoRequest.class)))
                .then((i) -> {
                    TodoRequest request = i.getArgument(0, TodoRequest.class);
                    return TodoEntity.builder()
                            .id(expect.getId())
                            .title(expect.getTitle())
                            .build();
                });

        TodoRequest request = new TodoRequest();
        request.setTitle("any title");

        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(request);

        this.mvc.perform(post("/")
                            .content(MediaType.APPLICATION_JSON_VALUE)
                            .contentType(content))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.title").value("any title"));

    }
}