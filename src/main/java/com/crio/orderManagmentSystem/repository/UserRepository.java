package com.crio.orderManagmentSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crio.orderManagmentSystem.model.User;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long>{

  Optional<User> findByUsername(String username);

  Optional<User> findByEmail(String email);

  Optional<User> findByPhone(String phone);

  com.crio.orderManagmentSystem.dto.UserDTO save(com.crio.orderManagmentSystem.dto.UserDTO user);
}
