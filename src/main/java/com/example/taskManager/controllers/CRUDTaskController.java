package com.example.taskManager.controllers;

import com.example.taskManager.dto.TaskCreateDTO;
import com.example.taskManager.dto.TaskDTO;
import com.example.taskManager.models.Tasks;
import com.example.taskManager.services.TaskService;
import com.example.taskManager.util.*;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class CRUDTaskController {

    private final ModelMapper modelMapper;
    private final TaskService taskService;
    private final TaskSaveValidator taskSaveValidator;

    public CRUDTaskController(ModelMapper modelMapper, TaskService taskService, TaskSaveValidator taskSaveValidator) {
        this.modelMapper = modelMapper;
        this.taskService = taskService;
        this.taskSaveValidator = taskSaveValidator;
    }

    @PostMapping()
    public ResponseEntity<HttpStatus> createNewTask(@RequestBody @Valid TaskCreateDTO taskCreateDTO, BindingResult bindingResult){

        taskSaveValidator.validate(convertToTask(taskCreateDTO), bindingResult);
        if (bindingResult.hasErrors()){
            StringBuilder error = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();

            for (FieldError fieldError : errors){
                error.append("Error on field: " + fieldError.getField()).append(" - ").append(fieldError.getDefaultMessage()).append(";    ");
            }

            throw new TaskNotValidException(error.toString());
        }

        taskService.save(convertToTask(taskCreateDTO));


        return ResponseEntity.ok(HttpStatus.OK);

    }


    public Tasks convertToTask(TaskCreateDTO taskCreateDTO){
        return modelMapper.map(taskCreateDTO, Tasks.class);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> taskNotValid(TaskNotValidException e){
        ErrorResponse response = new ErrorResponse(e.getMessage(), System.currentTimeMillis());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> taskAlreadyExist(TaskAlreadyExistException e){
        ErrorResponse response = new ErrorResponse(e.getMessage(), System.currentTimeMillis());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
