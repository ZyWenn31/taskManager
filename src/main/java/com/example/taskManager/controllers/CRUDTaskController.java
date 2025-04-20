package com.example.taskManager.controllers;

import com.example.taskManager.dto.*;
import com.example.taskManager.models.Tasks;
import com.example.taskManager.models.User;
import com.example.taskManager.services.TaskService;
import com.example.taskManager.util.*;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/task")
public class CRUDTaskController {

    private final ModelMapper modelMapper;
    private final TaskService taskService;
    private final TaskSaveValidator taskSaveValidator;
    private final TaskDeleteValidator taskDeleteValidator;
    private final TaskEditValidator taskEditValidator;
    private final UserExistValidator userExistValidator;

    public CRUDTaskController(ModelMapper modelMapper, TaskService taskService, TaskSaveValidator taskSaveValidator, TaskDeleteValidator taskDeleteValidator, TaskEditValidator taskEditValidator, UserExistValidator userExistValidator) {
        this.modelMapper = modelMapper;
        this.taskService = taskService;
        this.taskSaveValidator = taskSaveValidator;
        this.taskDeleteValidator = taskDeleteValidator;
        this.taskEditValidator = taskEditValidator;
        this.userExistValidator = userExistValidator;
    }

    @PostMapping()
    public ResponseEntity<HttpStatus> createNewTask(@RequestBody @Valid TaskCreateDTO taskCreateDTO, BindingResult bindingResult){

        taskSaveValidator.validate(convertToTask(taskCreateDTO), bindingResult);
        if (bindingResult.hasErrors()){
            throw new TaskNotValidException(CreateMessageError.createErrorMessage(bindingResult));
        }

        taskService.save(convertToTask(taskCreateDTO));


        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping
    public List<TaskDTO> getAllTasks(){
        return taskService.findAll().stream().map(this::convertToTaskDTO).collect(Collectors.toList());
    }

    @GetMapping("/my")
    public List<TaskDTO> getMyTasks(){
        return taskService.findMyTasks().stream().map(this::convertToTaskDTO).collect(Collectors.toList());
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteTask(@RequestBody @Valid TaskDeleteDTO taskDeleteDTO, BindingResult bindingResult){

        taskDeleteValidator.validate(convertToTask(taskDeleteDTO), bindingResult);
        if (bindingResult.hasErrors()){
            throw new TaskNotValidException(CreateMessageError.createErrorMessage(bindingResult));
        }

        taskService.delete(convertToTask(taskDeleteDTO));

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/editStatus")
    public ResponseEntity<HttpStatus> editStatus(@RequestBody @Valid TaskEditStatusDTO taskEditStatusDTO, BindingResult bindingResult){

        taskEditValidator.validate(convertToTask(taskEditStatusDTO), bindingResult);
        if (bindingResult.hasErrors()){
            throw new TaskNotValidException(CreateMessageError.createErrorMessage(bindingResult));
        }

        taskService.editStatus(convertToTask(taskEditStatusDTO));

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/editExecutor")
    public ResponseEntity<HttpStatus> editExecutor(@RequestBody @Valid TaskEditExecutorDTO taskEditExecutorDTO, BindingResult bindingResult){

        taskEditValidator.validate(convertToTask(taskEditExecutorDTO), bindingResult);
        userExistValidator.validate(convertToUser(taskEditExecutorDTO.getExecutor()), bindingResult);
        if (bindingResult.hasErrors()){
            throw new TaskNotValidException(CreateMessageError.createErrorMessage(bindingResult));
        }

        taskService.editExecutor(convertToTask(taskEditExecutorDTO));

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/editDescription")
    public ResponseEntity<HttpStatus> editDescription(@RequestBody @Valid TaskEditDescriptionDTO taskEditDescriptionDTO, BindingResult bindingResult){

        taskEditValidator.validate(convertToTask(taskEditDescriptionDTO), bindingResult);
        if (bindingResult.hasErrors()){
            throw new TaskNotValidException(CreateMessageError.createErrorMessage(bindingResult));
        }

        taskService.editDescription(convertToTask(taskEditDescriptionDTO));

        return ResponseEntity.ok(HttpStatus.OK);
    }


    private Tasks convertToTask(TaskCreateDTO taskCreateDTO){
        return modelMapper.map(taskCreateDTO, Tasks.class);
    }

    private Tasks convertToTask(TaskDeleteDTO task){
        return modelMapper.map(task, Tasks.class);
    }

    private Tasks convertToTask(TaskEditStatusDTO taskEditStatusDTO){
        return modelMapper.map(taskEditStatusDTO, Tasks.class);
    }

    private Tasks convertToTask(TaskEditExecutorDTO taskEditExecutorDTO){
        return modelMapper.map(taskEditExecutorDTO, Tasks.class);
    }

    private Tasks convertToTask(TaskEditDescriptionDTO taskEditDescriptionDTO){
        return modelMapper.map(taskEditDescriptionDTO, Tasks.class);
    }

    private TaskDTO convertToTaskDTO(Tasks tasks){
        return modelMapper.map(tasks, TaskDTO.class);
    }

    private User convertToUser(UserOutputDTO userOutputDTO){
        return modelMapper.map(userOutputDTO, User.class);
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

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> taskNotFound(TaskNotFoundException e){
        ErrorResponse response = new ErrorResponse(e.getMessage(), System.currentTimeMillis());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> userNotFound(UserNotFoundException e){
        ErrorResponse response = new ErrorResponse(e.getMessage(), System.currentTimeMillis());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
