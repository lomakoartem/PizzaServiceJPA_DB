package pizzaservice.repository.order;


import com.sun.org.apache.xpath.internal.operations.Or;
import pizzaservice.domain.customer.Customer;
import pizzaservice.domain.order.Order;

import java.util.List;

public interface OrderRepository{
    Order findById(Integer id);

    Order saveOrUpdate(Order entity);

    List<Order> findAll();

    void delete(Order entity);

    List<Order> findByCustomer(Customer customer);

}