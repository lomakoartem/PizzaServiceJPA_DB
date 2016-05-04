package pizzaservice;

import pizzaservice.domain.address.Address;
import pizzaservice.domain.customer.Customer;
import pizzaservice.domain.pizza.Pizza;
import pizzaservice.repository.pizza.InMemPizzaRepository;
import pizzaservice.repository.pizza.PizzaRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by lomak on 03.05.2016.
 */
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
public class JPAWithoutSpringPizzaApp {

    public static void main(String[] args) {
        EntityManagerFactory emf
                = Persistence.createEntityManagerFactory("jpa");
        EntityManager em = emf.createEntityManager();
/*

        Pizza pizza = new Pizza();
        pizza.setName("Margo");
        pizza.setPrice(120.3);
        pizza.setType(Pizza.Type.SEA);
*/


        Address address = new Address();
        address.setAddress("Kyiv");
        Customer customer = new Customer();
        customer.setName("Andrii");


        Customer customer1;
        //customer.setPhones(Arrays.asList("123", "456"));
        try {
            em.getTransaction().begin();
//            em.persist(pizza);
            em.persist(customer);
            em.getTransaction().commit();

            // System.out.println(address.getCustomer());

           // System.out.println(address.getId());
            //em.clear();
            //em.detach(customer);

        //    customer1 = em.find(Customer.class, 10);
            //em.detach(customer1);
            //  System.out.println(customer1.getPhones());
            //customer = em.merge(customer);
            //em.refresh(address);
            //TypedQuery<Customer> tq = em.createQuery("SELECT c FROM Customer c", Customer.class);
            //customers = tq.getResultList();

            //System.out.println(customers.get(0).getAddress());
        } finally {
            em.close();
            emf.close();
        }

    }
    }