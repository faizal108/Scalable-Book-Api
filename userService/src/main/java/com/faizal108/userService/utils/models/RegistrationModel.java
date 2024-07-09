package com.faizal108.userService.utils.models;

import com.faizal108.commanEntity.user.User;
import com.faizal108.userService.utils.ResponseModel;
import lombok.Data;

import java.security.NoSuchAlgorithmException;

import static com.faizal108.userService.utils.PasswordHashing.hashPassword;

@Data
public class RegistrationModel {

    private String firstName;
    private String lastName;
    private String email;
    private String contact;
    private String password;

/*
* This convertToUser method convert our register model to user.
* also convert the plain password to hash password.
* */
    public User convertToUser(){

        User user =new User();
        user.setFirstName(this.firstName);
        user.setLastName(this.lastName);
        try {
            user.setHashPassword(hashPassword(this.password));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        user.setEmail(this.email);
        user.setContact(this.contact);
        return user;
    }
}
