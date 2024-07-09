package com.faizal108.userService.controllers;

import com.faizal108.userService.services.UserService;
import com.faizal108.userService.utils.ResponseModel;
import com.faizal108.userService.utils.models.RegistrationModel;
import com.faizal108.userService.utils.models.UpdateModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/ping")
    public String ping(){
        return "Alive!!";
    }

    @PostMapping("/add")
    public ResponseModel addUser(@RequestBody RegistrationModel user){
        return userService.addUser(user);
    }

    @GetMapping("/all")
    public ResponseModel getAllUser(){
        return userService.getAllUser();
    }

    @GetMapping("/{id}")
    public ResponseModel getUserById(@PathVariable UUID id){
        return userService.getUserById(id);
    }

    @PutMapping("/update")
    public ResponseModel updateUser(@RequestBody UpdateModel user){
        return userService.updateUser(user);
    }

    @DeleteMapping("/del/{id}")
    public ResponseModel deleteUser(@PathVariable UUID id){
        return userService.deleteUser(id);
    }
}
