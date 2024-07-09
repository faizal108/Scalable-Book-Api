package com.faizal108.userService.services;

import com.faizal108.userService.utils.ResponseModel;
import com.faizal108.userService.utils.models.RegistrationModel;
import com.faizal108.userService.utils.models.UpdateModel;

import java.util.UUID;

public interface UserService {

    //This method Will handle the adding of User.
    ResponseModel addUser(RegistrationModel user);

    // This method will fetch user by its ID.
    ResponseModel getUserById(UUID id);
    //This method will update the user.
    ResponseModel updateUser(UpdateModel user);
    //This method will delete/remove the user.
    ResponseModel deleteUser(UUID id);
    //This method will get all the available users.
    ResponseModel getAllUser();
}
