package com.thehecklers.restdemo.repository;

import com.thehecklers.restdemo.domain.Coffee;
import org.springframework.data.repository.CrudRepository;

public interface CoffeeRepository extends CrudRepository<Coffee, String> {
}
