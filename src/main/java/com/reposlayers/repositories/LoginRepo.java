package com.reposlayers.repositories;

import com.reposlayers.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepo extends JpaRepository<User, Integer>{

    User findByUsername(String username);
}
