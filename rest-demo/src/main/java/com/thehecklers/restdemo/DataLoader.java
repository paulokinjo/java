package com.thehecklers.restdemo;

import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.annotation.PostConstruct;
import com.thehecklers.restdemo.domain.Coffee;
import com.thehecklers.restdemo.repository.CoffeeRepository;
import org.springframework.stereotype.Component;

@Component
public class DataLoader {

    private final CoffeeRepository coffeeRepository;


    public DataLoader(CoffeeRepository coffeeRepository) {
        this.coffeeRepository = coffeeRepository;
    }

    @PostConstruct
    private void loadData() {
        coffeeRepository.saveAll(Stream.of(
                new Coffee("Cafe Cereza"),
                new Coffee("Cafe Ganador"),
                new Coffee("Cafe Lareno"),
                new Coffee("Cafe Tres Pontas")
        ).collect(Collectors.toList()));
    }
}
