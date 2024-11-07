package com.bms.services;

import com.bms.dtos.SignupRequestDto;
import com.bms.models.User;
import com.bms.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public boolean login(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()==false) {
            throw new RuntimeException();

        }

        User user = userOptional.get();
        return encoder.matches(password,user.getPassword());

    }

    public User createUser(SignupRequestDto request) {


        Optional<User> user = userRepository.findByEmail(request.getEmail());

        if(user.isPresent()){
            throw new RuntimeException();
        }

        User newUser = new User();
        newUser.setEmail(request.getEmail());
        newUser.setPassword(encoder.encode(request.getPassword()));


        return userRepository.save(newUser);
    }
}
