package com.reposlayers.repositories;

import com.reposlayers.models.User;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

    User findByUsername(String username);
}

