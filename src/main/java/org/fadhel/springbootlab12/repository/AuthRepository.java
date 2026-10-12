package org.fadhel.springbootlab12.repository;

import org.fadhel.springbootlab12.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<User,Long> {

    User findUserById(Long id);
    User findUserByUsername(String username);
}
