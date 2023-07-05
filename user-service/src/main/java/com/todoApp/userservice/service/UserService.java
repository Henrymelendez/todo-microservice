package com.todoApp.userservice.service;

import com.todoApp.userservice.config.APIClient;
import com.todoApp.userservice.dto.APIResponse;
import com.todoApp.userservice.dto.TodoResponse;
import com.todoApp.userservice.dto.UserRequest;
import com.todoApp.userservice.dto.UserResponse;
import com.todoApp.userservice.model.User;
import com.todoApp.userservice.repository.UserRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    //private final WebClient.Builder webClientBuilder;
    private final APIClient apiClient;
    public UserResponse saveUser(UserRequest userRequest){
        // convert userRequest in to user
        User user = new User();
        user.setUserDescription(userRequest.getUserDescription());
        user.setUsername(userRequest.getUsername());
            userRepository.save(user);
        return UserResponse.builder()
                .id(user.getId())
                .userName(user.getUsername())
                .userDescription(user.getUserDescription())
                .build();
    }
    //@CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "getDefaultTodo")
    @Retry(name = "${spring.application.name}", fallbackMethod = "getDefaultTodo")
    public APIResponse getUserById(Long id) {

        User user = userRepository.findById(id).get();

      /*  TodoResponse[] todoResponse = webClientBuilder.build()
                .get()
                .uri("http://localhost:8081/api/todo/" + id)
                .retrieve()
                .bodyToMono(TodoResponse[].class)
                .block();*/

        List<TodoResponse> todoResponse = apiClient.getTodo(id);

        UserResponse userResponse = UserResponse
                .builder()
                .id(user.getId())
                .userName(user.getUsername())
                .userDescription(user.getUserDescription())
                .build();

        APIResponse apiResponse = new APIResponse();
        apiResponse.setUserResponse(userResponse);
        apiResponse.setTodoResponse(todoResponse);

        return apiResponse;
    }

    public List<UserResponse> getUsers(){
        List<User> usersList = userRepository.findAll();

        List<UserResponse> userResponseList = usersList.stream()
                .map(this::mapToUserResponse).toList();
        return userResponseList;
    }
    private UserResponse mapToUserResponse(User user){
        return UserResponse.builder()
                .id(user.getId())
                .userName(user.getUsername())
                .userDescription(user.getUserDescription())
                .build();
    }
    public APIResponse getDefaultTodo(Long id, Exception exception){

        User user = userRepository.findById(id).get();

      /*  TodoResponse[] todoResponse = webClientBuilder.build()
                .get()
                .uri("http://localhost:8081/api/todo/" + id)
                .retrieve()
                .bodyToMono(TodoResponse[].class)
                .block();*/

        //List<TodoResponse> todoResponse = apiClient.getTodo(id);

        TodoResponse todoResponse = TodoResponse.builder()
                .userId(user.getId())
                .id(user.getId())
                .description("none")
                .task("none")
                .build();


        UserResponse userResponse = UserResponse
                .builder()
                .id(user.getId())
                .userName(user.getUsername())
                .userDescription(user.getUserDescription())
                .build();

        APIResponse apiResponse = new APIResponse();
        apiResponse.setUserResponse(userResponse);
        apiResponse.setTodoResponse(List.of(todoResponse));

        return apiResponse;


    }
}
