package pizzaservice.service.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pizzaservice.domain.calculator.Calculator;
import pizzaservice.domain.customer.Customer;
import pizzaservice.domain.order.Order;
import pizzaservice.domain.pizza.Pizza;
import pizzaservice.repository.order.OrderRepository;
import pizzaservice.service.customer.CustomerService;

import java.util.List;


@Service
public class SimpleOrderService implements OrderService {
    
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private Calculator priceCalculator;
    @Autowired
    private CustomerService customerService;

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
    @Transactional
    public void setOrderStatus(Order order, Order.OrderStatus newStatus) {
        if(newStatus.equals(Order.OrderStatus.DONE)) {
            Customer customer = order.getCustomer();
            customer.getBonusCard().increaseBonusSize(order.getOrderPrice());
            customerService.saveOrUpdate(customer);
        }
        order.setOrderStatus(newStatus);
        saveOrUpdate(order);
    }

    @Override
    @Transactional
    public Order saveOrUpdate(Order order) {
        return orderRepository.saveOrUpdate(order);
    }
    
    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }
    
    @Override
    @Transactional
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