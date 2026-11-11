package com.rodri.dev.cuidar.controller;

import com.rodri.dev.cuidar.dto.LoginRequestDto;
import com.rodri.dev.cuidar.dto.UserDto;
import com.rodri.dev.cuidar.dto.UserRequestDto;
import com.rodri.dev.cuidar.entity.User;
import com.rodri.dev.cuidar.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody @Valid UserRequestDto userRequestDto) {
        User newUser = userService.createUser(userRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new UserDto(newUser));
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> userLogin(@RequestBody LoginRequestDto loginRequestDto) {
        UserDto user = userService.login(loginRequestDto);
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public List<UserDto> findAll() {
        return userService.findAll().stream()
                .map(UserDto :: new)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable Long id) {
        User user = userService.findById(id);

        return ResponseEntity.ok().body(new UserDto(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateById(@PathVariable Long id, @RequestBody @Valid UserRequestDto userRequestDto) {
        User updateUser = userService.updateById(id, userRequestDto);

        return  ResponseEntity.ok().body(new UserDto(updateUser));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        userService.findById(id);
        userService.deleteById(id);
    }

}
