package com.rodri.dev.cuidar.service;

import com.rodri.dev.cuidar.dto.UserRequestDto;
import com.rodri.dev.cuidar.entity.User;
import com.rodri.dev.cuidar.exception.ResourceNotFoundException;
import com.rodri.dev.cuidar.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(UserRequestDto userRequestDto) {
       User newUser = new User();
       newUser.setName(userRequestDto.name());
       newUser.setEmail(userRequestDto.email());
       newUser.setPassword(userRequestDto.password());

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

        updateUser.setName(userRequestDto.name());
        updateUser.setEmail(userRequestDto.email());
        updateUser.setPassword(userRequestDto.password());

        return userRepository.save(updateUser);
    }

    public void deleteById(Long id) {
        userRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Usuário não encontrado"));

        userRepository.deleteById(id);
    }
}
