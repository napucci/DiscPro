package com.capstone.discgolfstore.services;

import com.capstone.discgolfstore.dtos.UserDto;
import com.capstone.discgolfstore.entities.CartDisc;
import com.capstone.discgolfstore.entities.User;
import com.capstone.discgolfstore.repositories.CartDiscRepository;
import com.capstone.discgolfstore.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartDiscRepository cartDiscRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public List<String> addUser(UserDto userDto){
        List<String> response = new ArrayList<>();
        User user = new User(userDto);
        userRepository.saveAndFlush(user);
        response.add("Login success");
        return response;
    }

    @Override
    public List<String> userLogin(UserDto userDto){
        List<String> response = new ArrayList<>();
        Optional<User> userOptional = userRepository.findByUsername(userDto.getUsername());
        if(userOptional.isPresent()){
            if(passwordEncoder.matches(userDto.getPassword(), userOptional.get().getPassword())){
                response.add("Login Success");
                response.add(String.valueOf(userOptional.get().getId()));
            }
            else{
                response.add("Login Failed");
            }
        }
        return response;
    }



}
