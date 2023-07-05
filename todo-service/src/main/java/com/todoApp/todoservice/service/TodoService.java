package com.todoApp.todoservice.service;

import com.todoApp.todoservice.config.APICient;
import com.todoApp.todoservice.dto.CategoryResponse;
import com.todoApp.todoservice.dto.TodoRequest;
import com.todoApp.todoservice.dto.TodoResponse;
import com.todoApp.todoservice.model.Todo;
import com.todoApp.todoservice.repository.TodoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    private final APICient apiCient;

    public TodoResponse saveTodo(TodoRequest todoRequest){

        Todo todo = new Todo();
        todo.setDescription(todoRequest.getDescription());
        todo.setTask(todoRequest.getTask());
        todo.setUserId(todoRequest.getUserId());
        todo.setCategoryId(todoRequest.getCategoryId());

        Todo saveTodo = todoRepository.save(todo);

        CategoryResponse categoryResponse = apiCient.getCategory(saveTodo.getCategoryId());
        return TodoResponse.builder()
                .id(saveTodo.getId())
                .description(saveTodo.getDescription())
                .task(saveTodo.getTask())
                .userId(saveTodo.getUserId())
                .categoryResponse(categoryResponse)
                .build();
    }


    public List<TodoResponse> getTodoByUserId(Long userId){
        List<Todo> todoList = todoRepository.findByUserId(userId);

        List<TodoResponse> todoResponseList = todoList.stream()
                .map(this::maptoTodoResponse)
                .toList();
        return todoResponseList;
    }

    public TodoResponse getTodoById(Long id){
        Todo todo = todoRepository.findById(id).get();
        CategoryResponse categoryResponse = apiCient.getCategory(todo.getCategoryId());
        return TodoResponse.builder()
                .id(todo.getId())
                .task(todo.getTask() )
                .description(todo.getDescription())
                .userId(todo.getUserId())
                .categoryResponse(categoryResponse)
                .build();
    }

    public List<TodoResponse> getallTodos(){
        List<Todo> todoList = todoRepository.findAll();
        List<TodoResponse> todoResponseList = todoList.stream().map(this::maptoTodoResponse).toList();
        return todoResponseList;
    }
    private TodoResponse maptoTodoResponse(Todo todo){
        CategoryResponse categoryResponse = apiCient.getCategory(todo.getCategoryId());
        return TodoResponse.builder()
                .id(todo.getId())
                .task(todo.getTask())
                .description(todo.getDescription())
                .userId(todo.getUserId())
                .categoryResponse(categoryResponse)
                .build();
    }
}
