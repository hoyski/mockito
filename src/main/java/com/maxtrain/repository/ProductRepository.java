package com.maxtrain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maxtrain.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
