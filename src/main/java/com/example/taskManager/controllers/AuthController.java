package com.example.taskManager.controllers;


import com.example.taskManager.dto.LoginDTO;
import com.example.taskManager.dto.UserDTO;
import com.example.taskManager.models.User;
import com.example.taskManager.security.JWTUtil;
import com.example.taskManager.services.RegistrationService;
import com.example.taskManager.util.ErrorResponse;
import com.example.taskManager.util.UserNotValidException;
import com.example.taskManager.util.UserValidator;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final RegistrationService registrationService;
    private final UserValidator personValidator;
    private final JWTUtil jwtUtil;
    private final ModelMapper modelMapper;
    private final AuthenticationManager authenticationManager;

    public AuthController(RegistrationService registrationService, UserValidator personValidator, JWTUtil jwtUtil, ModelMapper modelMapper, AuthenticationManager authenticationManager) {
        this.registrationService = registrationService;
        this.personValidator = personValidator;
        this.jwtUtil = jwtUtil;
        this.modelMapper = modelMapper;
        this.authenticationManager = authenticationManager;
    }


    @GetMapping("/login")
    public String loginPage(){
        return "auth/login";
    }

    @GetMapping("/registration")
    public String registrationPage(User person){
        return "auth/registration";
    }

    @PostMapping("/registration")
    public Map<String, String> performRegistration(@RequestBody @Valid UserDTO userDTO,
                                                   BindingResult bindingResult){
        User user = convertToUser(userDTO);

        personValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()){
            StringBuilder error = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();

            for (FieldError fieldError : errors){
                error.append("Error on field: " + fieldError.getField()).append(" - ").append(fieldError.getDefaultMessage()).append(";    ");
            }

            throw new UserNotValidException(error.toString());
        }

        registrationService.register(user);

        return Map.of("jwt-token", jwtUtil.generateToken(userDTO.getEmail()));
    }

    @PostMapping("/login")
    public Map<String, String> perform_login(@RequestBody LoginDTO loginDTO){
        UsernamePasswordAuthenticationToken inputToken = new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword());

        try {
            authenticationManager.authenticate(inputToken);
        } catch (BadCredentialsException e){
            return Map.of("message", "Incorrect credentials");
        }

        String token = jwtUtil.generateToken(loginDTO.getEmail());

        return Map.of("jwt-token", token);
    }

    private User convertToUser(UserDTO userDTO){
        return modelMapper.map(userDTO, User.class);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> userNotValid(UserNotValidException e){
        ErrorResponse response = new ErrorResponse(e.getMessage(), System.currentTimeMillis());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}