package com.matheus.projects.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matheus.projects.course.entities.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
