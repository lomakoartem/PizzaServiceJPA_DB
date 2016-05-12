package pizzaservice.repository.pizza;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pizzaservice.domain.pizza.Pizza;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;

@Repository
@Transactional
public class JpaPizzaRepository implements PizzaRepository {
    
    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional(readOnly = true)
    public Pizza findById(Integer id) {
        return em.find(Pizza.class, id);
    }
    
    @Override    
    public Pizza saveOrUpdate(Pizza pizza) {
        if(pizza.getId() == null) {
            em.persist(pizza);
        } else {
            em.merge(pizza);
        }
        return pizza;
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Pizza> findAll() {
        List<Pizza> pizzas = em.createQuery("from Pizza", Pizza.class).getResultList();
        Collections.sort(pizzas);
        return pizzas;
    }

    @Override    
    public void delete(Pizza pizza) {
        Pizza p = em.merge(pizza);
        em.remove(p);
    }
    
}
