package pizzaservice.repository.order;


import pizzaservice.domain.customer.Customer;
import pizzaservice.domain.order.Order;
import pizzaservice.repository.GenericRepository;

import java.util.List;

public interface OrderRepository extends GenericRepository<Order> {
    
    List<Order> findByCustomer(Customer customer);
    
}
