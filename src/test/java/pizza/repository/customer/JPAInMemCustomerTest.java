package pizza.repository.customer;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import pizzaservice.domain.customer.Customer;
import pizzaservice.repository.GenericRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by lomak on 12.05.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/resources/repositoryTestContext.xml"})
public class JPAInMemCustomerTest  {

    @Autowired
    private GenericRepository<Customer> customerRepository;

    private Customer customer;

    @Before
    public void initMethod() {
        customer = new Customer("Customer name");
    }

    @Test
    public void getAllCustomers() {
        customerRepository.saveOrUpdate(customer);
        customerRepository.findAll();
    }

    @Test
    public void testGetCustomerAndSaveCustomer() {
        customerRepository.saveOrUpdate(customer);
        Customer expected = customer;
        Customer result = customerRepository.findById(customer.getId());
        assertEquals(expected, result);
        assertEquals(expected.getName(), result.getName());
    }

    @Test
    public void testUpdateCustomer() {
        customerRepository.saveOrUpdate(customer);
        String updatedName = "Updated name";
        Customer updatedCustomer = new Customer(updatedName);
        updatedCustomer.setId(customer.getId());
        customerRepository.saveOrUpdate(updatedCustomer);
        Customer expected = updatedCustomer;
        Customer result = customerRepository.findById(customer.getId());
        assertEquals(expected, result);
        assertEquals(expected.getName(), result.getName());
    }
}