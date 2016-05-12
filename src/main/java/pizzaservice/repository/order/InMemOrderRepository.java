package pizzaservice.repository.order;

import pizzaservice.domain.customer.Customer;
import pizzaservice.domain.order.Order;

import java.util.ArrayList;
import java.util.List;

//@Repository
public class InMemOrderRepository implements OrderRepository {

    private final List<Order> orders = new ArrayList<Order>();

    public Order saveOrUpdate(Order order) {
        setOrderId(order);
        orders.add(order);
        return order;
    }

    private void setOrderId(Order order) {
        order.setId(orders.size());
    }

    @Override
    public Order findById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Order> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Order entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Order> findByCustomer(Customer customer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
