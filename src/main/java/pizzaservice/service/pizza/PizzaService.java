package pizzaservice.service.pizza;

import pizzaservice.domain.pizza.Pizza;

import java.util.List;

public interface PizzaService {
    Pizza find(Integer id);
    Pizza saveOrUpdate(Pizza pizza);
    List<Pizza> findAll();
    void delete(Pizza pizza);
}
