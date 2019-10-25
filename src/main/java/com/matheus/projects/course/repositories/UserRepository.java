package com.matheus.projects.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.matheus.projects.course.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
