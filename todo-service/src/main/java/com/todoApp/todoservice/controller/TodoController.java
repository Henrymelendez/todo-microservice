package com.todoApp.todoservice.controller;

import com.todoApp.todoservice.dto.TodoRequest;
import com.todoApp.todoservice.dto.TodoResponse;
import com.todoApp.todoservice.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
@RequiredArgsConstructor
public class TodoController {


        private final TodoService todoService;

        @PostMapping
        @ResponseStatus(HttpStatus.CREATED)
        public TodoResponse createTodo(@RequestBody TodoRequest todoRequest){
            TodoResponse todo = todoService.saveTodo(todoRequest);
            return todo;
        }
        @GetMapping("/{userid}")
        @ResponseStatus(HttpStatus.OK)
        public List<TodoResponse> getTodo(@PathVariable("userid") Long userid){
            List<TodoResponse> todoResponse = todoService.getTodoByUserId(userid);
            return todoResponse;
        }
        @GetMapping
        @ResponseStatus(HttpStatus.OK)
        public List<TodoResponse> getAllTodos(){
            List<TodoResponse> todoResponseList = todoService.getallTodos();
            return todoResponseList;
        }




}
