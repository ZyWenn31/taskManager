package com.example.taskManager.controllers;

import com.example.taskManager.dto.taskDTO.TaskDTO;
import com.example.taskManager.dto.userDTO.UserOutputDTO;
import com.example.taskManager.dto.userDTO.UserTasksDTO;
import com.example.taskManager.models.Tasks;
import com.example.taskManager.models.User;
import com.example.taskManager.services.TaskService;
import com.example.taskManager.util.CreateMessageError;
import com.example.taskManager.util.ErrorResponse;
import com.example.taskManager.util.exception.SizeAndPageException;
import com.example.taskManager.util.exception.UserNotFoundException;
import com.example.taskManager.util.exception.UserNotValidException;
import com.example.taskManager.util.validator.UserExistValidator;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GetTaskController {

    private final UserExistValidator userExistValidator;
    private final ModelMapper modelMapper;
    private final TaskService taskService;

    public GetTaskController(UserExistValidator userExistValidator, ModelMapper modelMapper, TaskService taskService) {
        this.userExistValidator = userExistValidator;
        this.modelMapper = modelMapper;
        this.taskService = taskService;
    }



    //Добавить новый DTO, соответсвующий измененному сервису, сделать такой же метод для поиска по исполнителю
    @GetMapping("/getTaskForAuthor")
    public List<TaskDTO> getTaskForAuthor(@RequestBody @Valid UserTasksDTO userTasksDTO,
                                          BindingResult bindingResult){

        userExistValidator.validate(convertToUser(userTasksDTO), bindingResult);
        if (bindingResult.hasErrors()){
            throw new UserNotValidException(CreateMessageError.createErrorMessage(bindingResult));
        }

        return taskService.findAllByAuthor(convertToUser(userTasksDTO), userTasksDTO.isPagination(), userTasksDTO.isSorting(), userTasksDTO.getTitle(), userTasksDTO.getPage(), userTasksDTO.getSize()).stream().map(this::convertToTaskDTO).toList();
    }

    @GetMapping("/getTaskForExecutor")
    public  List<TaskDTO> getTaskForExecutor(@RequestBody @Valid UserTasksDTO userTasksDTO,
                                             BindingResult bindingResult){

        userExistValidator.validate(convertToUser(userTasksDTO), bindingResult);
        if (bindingResult.hasErrors()){
            throw new UserNotValidException(CreateMessageError.createErrorMessage(bindingResult));
        }

        return taskService.findAllByExecutor(convertToUser(userTasksDTO), userTasksDTO.isPagination(), userTasksDTO.isSorting(), userTasksDTO.getTitle(), userTasksDTO.getPage(), userTasksDTO.getSize()).stream().map(this::convertToTaskDTO).toList();
    }

    private User convertToUser(UserOutputDTO userOutputDTO){
        return modelMapper.map(userOutputDTO, User.class);
    }

    private User convertToUser(UserTasksDTO userTasksDTO){
        return modelMapper.map(userTasksDTO, User.class);
    }

    private TaskDTO convertToTaskDTO(Tasks tasks){
        return modelMapper.map(tasks, TaskDTO.class);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> userNotFound(UserNotFoundException e){
        ErrorResponse response = new ErrorResponse(e.getMessage(), System.currentTimeMillis());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> userValid(UserNotValidException e){
        ErrorResponse response = new ErrorResponse(e.getMessage(), System.currentTimeMillis());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> sizeAndPageNotValid(SizeAndPageException e){
        ErrorResponse response = new ErrorResponse(e.getMessage(), System.currentTimeMillis());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
