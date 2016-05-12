package pizzaservice.service.customer;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pizzaservice.domain.customer.Customer;
import pizzaservice.repository.customer.CustomerRepository;

@Service

public class SimpleCustomerService implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    @Transactional
    public Customer saveOrUpdate(Customer customer) {
        return customerRepository.saveOrUpdate(customer);
    }      


}
