package com.example.onlineshop.security.repositories;

import com.example.onlineshop.security.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmailUser(String email);
    boolean existsByEmailUser(String cnp);

}
