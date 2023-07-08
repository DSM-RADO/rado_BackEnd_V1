package com.example.rado.domain.user.domain.repository;

import com.example.rado.domain.user.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findUserByUserId(String accountId);
    boolean existsByUserId(String accountId);
}
