package pizzaservice.service.orderService;


import pizzaservice.domain.customer.Customer;
import pizzaservice.domain.order.Order;
import pizzaservice.domain.pizza.Pizza;

import java.util.List;

public interface OrderService {

	void deletePizzaFomrOrder(Order order, Pizza pizza);
	void addPizzaToOrder(Order order, Pizza pizza);
	Order saveOrUpdate(Order order);
	Order findById(Integer id);
	List<Order> findAll();
	List<Order> findByCustomer(Customer customer);
	void setOrderStatus(Order order, Order.OrderStatus newStatus);
	void deleteOrder(Order order);
}