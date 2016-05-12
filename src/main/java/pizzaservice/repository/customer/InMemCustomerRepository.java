package pizzaservice.repository.customer;

import pizzaservice.domain.customer.Customer;
import pizzaservice.repository.GenericRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lomak on 12.05.2016.
 */
public class InMemCustomerRepository implements GenericRepository<Customer> {
    private List<Customer> customers;
    @Override
    public Customer findById(Integer id) {
        return null;
    }

    @Override
    public Customer saveOrUpdate(Customer entity) {
        if (customers == null) {
            customers = new ArrayList<Customer>();
        }
        customers.add(entity);
        return entity;
    }

    @Override
    public List<Customer> findAll() {
        if (customers == null) {
            customers = new ArrayList<Customer>();
        }
        return customers;
    }

    @Override
    public void delete(Customer entity) {

    }
}
