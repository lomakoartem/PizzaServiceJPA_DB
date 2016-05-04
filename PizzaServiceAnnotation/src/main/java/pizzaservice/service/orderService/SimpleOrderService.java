package pizzaservice.service.orderService;

import org.springframework.beans.factory.annotation.Autowired;
import pizzaservice.domain.calculator.Calculator;
import pizzaservice.domain.customer.Customer;
import pizzaservice.domain.order.Order;
import pizzaservice.domain.pizza.Pizza;
import pizzaservice.repository.order.OrderRepository;
import pizzaservice.repository.pizza.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


public class SimpleOrderService implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private Calculator priceCalculator;


    public SimpleOrderService() {
    }

    public SimpleOrderService(OrderRepository orderRepository,
                              Calculator priceCalculator) {
        this.orderRepository = orderRepository;
        this.priceCalculator = priceCalculator;
    }

    @Override
    public void addPizzaToOrder(Order order, Pizza pizza) {
        order.addPizza(pizza);
        order.setOrderPrice(calcOrderPrice(order));
    }

    @Override
    public void deletePizzaFomrOrder(Order order, Pizza pizza) {
        order.deletePizza(pizza);
        order.setOrderPrice(calcOrderPrice(order));
    }

    private Double calcOrderPrice(Order order) {
        return priceCalculator.calculatePrice(order);
    }

    @Override
    public void setOrderStatus(Order order, Order.OrderStatus newStatus) {
        if(newStatus.equals(Order.OrderStatus.DONE)) {
            Customer customer = order.getCustomer();
            customer.getBonusCard().increaseBonusSize(order.getOrderPrice());
          //  customerService.saveOrUpdate(customer);
        }
        order.setOrderStatus(newStatus);
        saveOrUpdate(order);
    }

    @Override
    public Order saveOrUpdate(Order order) {
        return orderRepository.saveOrUpdate(order);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> findByCustomer(Customer customer) {
        return orderRepository.findByCustomer(customer);
    }

    @Override
    public Order findById(Integer id) {
        return orderRepository.findById(id);
    }

    @Override
    public void deleteOrder(Order order) {
        orderRepository.delete(order);
    }

}