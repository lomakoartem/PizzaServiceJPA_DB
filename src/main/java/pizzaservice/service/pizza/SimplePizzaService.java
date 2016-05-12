package pizzaservice.service.pizza;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pizzaservice.domain.pizza.Pizza;
import pizzaservice.repository.pizza.PizzaRepository;

import java.util.List;

@Service
@Transactional
public class SimplePizzaService implements PizzaService {
    
    @Autowired
    PizzaRepository pizzaRepository;

    public SimplePizzaService() {
    }

    public SimplePizzaService(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }
    
    @Override
    public Pizza saveOrUpdate(Pizza pizza) {
        return pizzaRepository.saveOrUpdate(pizza);
    }
    
    @Override    
    public Pizza find(Integer id) {
        return pizzaRepository.findById(id);
    }
    
    @Override
    public List<Pizza> findAll() {
        return  pizzaRepository.findAll();
    }

    @Override    
    public void delete(Pizza pizza) {
        pizzaRepository.delete(pizza);
    }
}
