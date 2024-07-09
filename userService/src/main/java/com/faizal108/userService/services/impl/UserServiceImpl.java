package com.faizal108.userService.services.impl;

import com.faizal108.commanEntity.user.User;
import com.faizal108.userService.Repository.UserRepository;
import com.faizal108.userService.services.UserService;
import com.faizal108.userService.utils.CommonUtil;
import com.faizal108.userService.utils.ResponseModel;
import com.faizal108.userService.utils.models.RegistrationModel;
import com.faizal108.userService.utils.models.UpdateModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    @Override
    public ResponseModel addUser(RegistrationModel user) {
        try{
            userRepository.save(user.convertToUser());
            return CommonUtil.create(null, HttpStatus.OK);
        }catch (Exception e){
            return CommonUtil.create(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseModel getUserById(UUID id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(value -> CommonUtil.create(value, HttpStatus.OK)).orElseGet(() -> CommonUtil.create(null, HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseModel updateUser(UpdateModel user) {

        try{
            Optional<User> existingUser = userRepository.findById(UUID.fromString(user.getId()));

            if(existingUser.isPresent()) {
                existingUser.get().setFirstName(user.getFirstName());
                existingUser.get().setLastName(user.getLastName());
                existingUser.get().setEmail(user.getEmail());
                existingUser.get().setContact(user.getContact());
                existingUser.get().setFirstName(user.getFirstName());

                userRepository.save(existingUser.get());

                return CommonUtil.create(null,HttpStatus.OK);
            }
            return CommonUtil.create(null,HttpStatus.OK);
        }catch (Exception e){
            return CommonUtil.create(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseModel deleteUser(UUID id) {
        Optional<User> user = userRepository.findById(id);

        try {
            if(user.isPresent()){
                userRepository.deleteById(id);
                return CommonUtil.create(null,HttpStatus.OK);
            }
            return CommonUtil.create(null,HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return CommonUtil.create(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseModel getAllUser() {
        try{
            List<User> users = userRepository.findAll();
            return CommonUtil.create(users,HttpStatus.OK);
        }catch(Exception e){
            return CommonUtil.create(null,HttpStatus.NOT_FOUND);
        }
    }
}
