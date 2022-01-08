package com.thehecklers.restdemo.api;

import java.util.Optional;
import com.thehecklers.restdemo.domain.Coffee;
import com.thehecklers.restdemo.repository.CoffeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/1.0/coffees")
public class CoffeeController {
    @Autowired
    CoffeeRepository repo;

    public CoffeeController() {}

    @GetMapping
    Iterable<Coffee> getCoffees() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    Optional<Coffee> getCoffeeById(@PathVariable String id) {
        return repo.findById(id);
    }

    @PostMapping
    Coffee postCoffee(@RequestBody Coffee coffee) {
        return repo.save(coffee);
    }

    @PutMapping("/{id}")
    ResponseEntity<Coffee> putCoffee(@PathVariable String id, @RequestBody Coffee coffee) {
        return repo.existsById(id)
                ? new ResponseEntity<>(repo.save(coffee), HttpStatus.OK)
                : new ResponseEntity<>(repo.save(coffee), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    void deleteCoffee(@PathVariable String id) {
        repo.deleteById(id);
    }
}
