package com.todoApp.userservice.config;

import com.todoApp.userservice.dto.TodoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@FeignClient(name = "TODO-SERVICE")
public interface APIClient {
    @GetMapping("api/todo/{userid}")
    @ResponseStatus(HttpStatus.OK)
    List<TodoResponse> getTodo(@PathVariable("userid") Long userid);

}
