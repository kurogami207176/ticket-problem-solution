package com.alaindroid.sportsbet;

import com.alaindroid.sportsbet.model.Customer;
import com.alaindroid.sportsbet.model.Ticket;
import com.alaindroid.sportsbet.model.TicketType;
import com.alaindroid.sportsbet.transport.model.OrderRequest;
import com.alaindroid.sportsbet.transport.model.OrderResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EndToEndTests {

	@Value(value="${local.server.port}")
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	private SecureRandom random = new SecureRandom();


	@Test
	void test__Empty() {
		String url = "http://localhost:" + port + "/order";
		// Given
		OrderRequest request = new OrderRequest(1, Collections.emptyList());
		// When
		ResponseEntity<OrderResponse> response = restTemplate.postForEntity(url, request, OrderResponse.class);
		// Then
		assertThat(response.getStatusCode().value()).isEqualTo(200);
	}

	@Test
	void test__Senior_1_Children_2__Total27_5() {
		String url = "http://localhost:" + port + "/order";
		// Given
		int txnId = random.nextInt();
		// And
		Customer senior = new Customer("John Smith", 70);
		// And
		Customer childA = new Customer("Jane Doe", 5);
		Customer childB = new Customer("Bob Doe", 6);
		// And
		OrderRequest request = new OrderRequest(txnId, Arrays.asList(senior, childA, childB));
		// When
		ResponseEntity<OrderResponse> response = restTemplate.postForEntity(url, request, OrderResponse.class);
		// Then
		assertThat(response.getBody().totaCost()).isEqualByComparingTo(BigDecimal.valueOf(27.50));
		// And
		assertTicketFromList(response.getBody(), 0, TicketType.CHILDREN, 2, 10.00);
		// SENIOR
		assertTicketFromList(response.getBody(), 1, TicketType.SENIOR, 1, 17.50);
	}

	@Test
	void test__Adult_1_Teen_1_Children_3__Total48_25() {
		String url = "http://localhost:" + port + "/order";
		// Given
		int txnId = random.nextInt();
		// And
		Customer senior = new Customer("Billy Kidd", 36);
		// And
		Customer childA = new Customer("Zoe Daniels", 3);
		Customer childB = new Customer("George White", 8);
		Customer childC = new Customer("Tommy Anderson", 9);
		Customer teen = new Customer("Joe Smith", 17);
		// And
		OrderRequest request = new OrderRequest(txnId, Arrays.asList(senior, childA, childB, childC, teen));
		// When
		ResponseEntity<OrderResponse> response = restTemplate.postForEntity(url, request, OrderResponse.class);
		System.out.println(response.getBody());
		// Then
		assertThat(response.getBody().totaCost()).isEqualByComparingTo(BigDecimal.valueOf(48.25));
		// And
		assertTicketFromList(response.getBody(), 0, TicketType.ADULT, 1, 25.00);
		// SENIOR
		assertTicketFromList(response.getBody(), 1, TicketType.CHILDREN, 3, 11.25);
		assertTicketFromList(response.getBody(), 2, TicketType.TEEN, 1, 12.00);
	}

	@Test
	void test__All_1__Total59_5() {
		String url = "http://localhost:" + port + "/order";
		// Given
		int txnId = random.nextInt();
		// And
		Customer adult = new Customer("Jesse James", 36);
		// And
		Customer senior = new Customer("Daniel Anderson", 95);
		Customer teen = new Customer("Mary Jones", 15);
		Customer child = new Customer("Michelle Parker", 10);
		OrderRequest request = new OrderRequest(txnId, Arrays.asList(adult, senior, teen, child));
		// When
		ResponseEntity<OrderResponse> response = restTemplate.postForEntity(url, request, OrderResponse.class);
		// Then
		assertThat(response.getBody().totaCost()).isEqualByComparingTo(BigDecimal.valueOf(59.50));
		// And
		assertTicketFromList(response.getBody(), 0, TicketType.ADULT, 1, 25.00);
		// SENIOR
		assertTicketFromList(response.getBody(), 1, TicketType.CHILDREN, 1, 5.00);
		assertTicketFromList(response.getBody(), 2, TicketType.SENIOR, 1, 17.50);
		assertTicketFromList(response.getBody(), 3, TicketType.TEEN, 1, 12.00);
	}

	private static void assertTicketFromList(OrderResponse response, int index,
											 TicketType ticketType,
											 int quantity,
											 double totalCost) {
		Ticket ticketA = response.tickets().get(index);
		assertThat(ticketA.ticketType()).isEqualTo(ticketType);
		assertThat(ticketA.quantity()).isEqualTo(quantity);
		assertThat(ticketA.totalCost()).isEqualByComparingTo(BigDecimal.valueOf(totalCost));
	}

}
