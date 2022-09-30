package vttp2022.assessment.csf.orderbackend.services;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp2022.assessment.csf.orderbackend.models.Order;
import vttp2022.assessment.csf.orderbackend.models.OrderSummary;
import vttp2022.assessment.csf.orderbackend.repositories.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepo;
	@Autowired
	private PricingService priceSvc;

	// POST /api/order
	// Create a new order by inserting into orders table in pizzafactory database
	// IMPORTANT: Do not change the method's signature
	public void createOrder(Order order) {
		orderRepo.postOrder(order.getOrderId(), order.getName(),
				order.getEmail(), order.getSize(), order.isThickCrust(), order.getToppings().toString(), order.getSauce(),
				order.getComments());

	}

	// GET /api/order/<email>/all
	// Get a list of orders for email from orders table in pizzafactory database
	// IMPORTANT: Do not change the method's signature
	public Optional<OrderSummary> getOrdersByEmail(String email) {
		
		// Order orders = orderRepo.getOrdersByEmail(email);

		return orderRepo.getOrdersByEmail(email);
		// Use priceSvc to calculate the total cost of an order
	}
}
