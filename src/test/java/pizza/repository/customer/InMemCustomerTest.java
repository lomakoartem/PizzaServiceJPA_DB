package pizza.repository.customer;

import static org.junit.Assert.*;

import org.junit.Test;
import pizzaservice.domain.address.Address;
import pizzaservice.domain.card.Card;
import pizzaservice.domain.customer.Customer;
import pizzaservice.repository.GenericRepository;
import pizzaservice.repository.customer.CustomerRepository;
import pizzaservice.repository.customer.InMemCustomerRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by lomak on 12.05.2016.
 */
public class InMemCustomerTest {
    private Card bonusCard = new Card(200.0);

    private Customer customer = new Customer(bonusCard, "Artem");

    @Test
    public void testSaveCustomer() {
        GenericRepository<Customer> repository = new InMemCustomerRepository();
        int expected = 0;
        int result = repository.findAll().size();
        assertEquals(expected, result);
        Customer expectedCustomer = customer;
        repository.saveOrUpdate(customer);
        Customer resultCustomer = repository.findAll().get(0);
        assertEquals(expectedCustomer, resultCustomer);
        repository.saveOrUpdate(customer);
        resultCustomer = repository.findAll().get(0);
        assertEquals(expectedCustomer, resultCustomer);
        resultCustomer = repository.findAll().get(1);
        assertEquals(expectedCustomer, resultCustomer);
    }

}