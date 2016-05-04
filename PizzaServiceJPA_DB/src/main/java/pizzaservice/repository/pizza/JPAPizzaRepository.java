package pizzaservice.repository.pizza;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pizzaservice.domain.pizza.Pizza;
import pizzaservice.repository.pizza.PizzaRepository;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository("pizzaRepository")
@Transactional
public class JPAPizzaRepository implements PizzaRepository {

    @PersistenceContext
    private EntityManager em;
//    @PersistenceUnit()
//    private EntityManagerFactory emf;

    @Override
    public Pizza findById(Integer id) {
        return null;
    }

    @Override
    public Pizza saveOrUpdate(Pizza entity) {
        return null;
    }

    @Override
    public List<Pizza> findAll() {
        return null;
    }

    @Override
    public void delete(Pizza entity) {

    }

    @Override
    @Transactional(readOnly = true)
    public Pizza getPizzaByID(Integer id) {
        return em.find(Pizza.class, id);
    }

    @Override    
    public Pizza create(Pizza pizza) {
        em.persist(pizza);
        return pizza;
    }

}
