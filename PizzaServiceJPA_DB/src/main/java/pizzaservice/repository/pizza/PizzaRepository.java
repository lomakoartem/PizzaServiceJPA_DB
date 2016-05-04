package pizzaservice.repository.pizza;


import pizzaservice.domain.pizza.Pizza;

import java.util.List;

public interface PizzaRepository {
	Pizza findById(Integer id);
	Pizza saveOrUpdate(Pizza entity);
	List<Pizza> findAll();
	void delete(Pizza entity);
	Pizza getPizzaByID(Integer id);
	Pizza create(Pizza pizza);

	//Pizza getPizzaByID(Integer id);
}
