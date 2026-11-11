package com.rodri.dev.cuidar.repository;

import com.rodri.dev.cuidar.dto.UserDto;
import com.rodri.dev.cuidar.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
