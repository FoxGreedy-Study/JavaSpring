package com.matheus.projects.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matheus.projects.course.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
