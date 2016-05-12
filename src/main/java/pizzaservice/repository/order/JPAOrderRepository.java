package pizzaservice.repository.order;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import pizzaservice.domain.customer.Customer;
import pizzaservice.domain.order.Order;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class JPAOrderRepository implements OrderRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional(readOnly = true)
    public Order findById(Integer id) {
        return em.find(Order.class, id);
    }

    @Override
    public Order saveOrUpdate(Order order) {
        if (order.getId() == null) {
            em.persist(order);
        } else {
            em.merge(order);
        }
        return order;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Order> findAll() {
        List<Order> orders = em.createQuery("from Order", Order.class).getResultList();
        return orders;
    }

    @Override
    public void delete(Order order) {
        Order p = em.merge(order);
        em.remove(p);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Order> findByCustomer(Customer customer) {
        TypedQuery<Order> query = em.createNamedQuery("Order.findByCustomer", Order.class);
        query.setParameter("customer", customer);
        List<Order> orders = query.getResultList();
        return CollectionUtils.isEmpty(orders) ? null : orders;
    }

}
