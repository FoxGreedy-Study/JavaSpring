package com.matheus.projects.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matheus.projects.course.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
