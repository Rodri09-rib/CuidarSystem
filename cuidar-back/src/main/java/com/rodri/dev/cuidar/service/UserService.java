package com.rodri.dev.cuidar.service;

import com.rodri.dev.cuidar.dto.UserRequestDto;
import com.rodri.dev.cuidar.entity.User;
import com.rodri.dev.cuidar.exception.ResourceNotFoundException;
import com.rodri.dev.cuidar.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;


    }

    public User createUser(UserRequestDto userRequestDto) {
       User newUser = new User();
       String passCript = passwordEncoder.encode(userRequestDto.password());
       newUser.setName(userRequestDto.name());
       newUser.setEmail(userRequestDto.email());
       newUser.setPassword(passCript);


       return userRepository.save(newUser);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id ) {
       return userRepository.findById(id).orElseThrow(()
               -> new ResourceNotFoundException("Usuário não encontrado"));
    }

    public User updateById(Long id, UserRequestDto userRequestDto) {
        User updateUser = userRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Usuário não encontrado"));

        String newPassCript = passwordEncoder.encode(userRequestDto.password());
        updateUser.setName(userRequestDto.name());
        updateUser.setEmail(userRequestDto.email());
        updateUser.setPassword(newPassCript);

        return userRepository.save(updateUser);
    }

    public void deleteById(Long id) {
        userRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Usuário não encontrado"));

        userRepository.deleteById(id);
    }
}
