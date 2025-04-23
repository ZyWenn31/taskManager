package com.example.taskManager.controllers;

import com.example.taskManager.dto.CommentDTO;
import com.example.taskManager.models.Comments;
import com.example.taskManager.services.CommentService;
import com.example.taskManager.services.TaskService;
import com.example.taskManager.services.UserService;
import com.example.taskManager.util.CreateMessageError;
import com.example.taskManager.util.ErrorResponse;
import com.example.taskManager.util.exception.CommentNotValidException;
import com.example.taskManager.util.exception.TaskNotFoundException;
import com.example.taskManager.util.exception.UserNotFoundException;
import com.example.taskManager.util.validator.CommentAddValidator;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {

    private final ModelMapper modelMapper;
    private final CommentAddValidator commentAddValidator;
    private final UserService userService;
    private final TaskService taskService;
    private final CommentService commentService;

    public CommentController(ModelMapper modelMapper, CommentAddValidator commentAddValidator, UserService userService, TaskService taskService, CommentService commentService) {
        this.modelMapper = modelMapper;
        this.commentAddValidator = commentAddValidator;
        this.userService = userService;
        this.taskService = taskService;
        this.commentService = commentService;
    }

    @PostMapping("/taskComment")
    public ResponseEntity<HttpStatus> addComment(@RequestBody @Valid CommentDTO commentDTO,
                                                 BindingResult bindingResult){

        //commentAddValidator.validate(convertToComments(commentDTO), bindingResult);
        if (bindingResult.hasErrors()){
            throw new CommentNotValidException(CreateMessageError.createErrorMessage(bindingResult));
        }

        commentService.save(convertToComments(commentDTO));

        return new ResponseEntity<>(HttpStatus.OK);
    }

    private Comments convertToComments(CommentDTO commentDTO){
        Comments comment = modelMapper.map(commentDTO, Comments.class);
        comment.setTask(taskService.findTaskByAuthorAndTitle(userService.findUserByUsername(commentDTO.getAuthor().getUsername()), commentDTO.getTaskTitle()));
        return comment;
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> commentNotValidException(CommentNotValidException e){
        ErrorResponse response = new ErrorResponse(e.getMessage(), System.currentTimeMillis());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> userNotFound(UserNotFoundException e){
        ErrorResponse response = new ErrorResponse(e.getMessage(), System.currentTimeMillis());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
