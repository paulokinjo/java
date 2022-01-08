package com.thehecklers.restdemo.api;

import com.thehecklers.restdemo.domain.Coffee;
import com.thehecklers.restdemo.repository.CoffeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class CoffeeControllerTest {
	public static final String API_1_0_COFFEES = "/api/1.0/coffees";

	@Autowired
	CoffeeRepository coffeeRepository;

	@Autowired
	TestRestTemplate testRestTemplate;

	@BeforeEach
	public void clean() {
		coffeeRepository.deleteAll();
	}

	@Test
	public void postCoffee_whenCoffeeIsValid_receiveOk() {
		Coffee coffee = createValidCoffee();

		ResponseEntity<Object> response = testRestTemplate.postForEntity(API_1_0_COFFEES, coffee, Object.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Test
	public void postCoffee_whenCoffeeIsValid_coffeeSavedToDatabase() {
		Coffee coffee = createValidCoffee();
		testRestTemplate.postForEntity(API_1_0_COFFEES, coffee, Object.class);
		assertThat(coffeeRepository.count()).isEqualTo(1);
	}

	private Coffee createValidCoffee() {
		Coffee coffee = new Coffee();
		coffee.setId("someid");
		coffee.setName("test-coffee");
		return coffee;
	}
}
