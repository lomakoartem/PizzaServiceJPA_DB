package pizzaservice.repository.pizza;

import org.springframework.stereotype.Repository;
import pizzaservice.domain.pizza.Pizza;
import pizzaservice.infrastructure.Benchmark;


import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class InMemPizzaRepository implements PizzaRepository {

    List<Pizza> pizzas = new ArrayList<>();

  /*  @PostConstruct
    public void cookPizzas() {
        pizzas.add(new Pizza(1, "Margarita", 60d, Pizza.Type.MEAT));
        pizzas.add(new Pizza(2, "SeaPizza", 90d, Pizza.Type.SEA));
        pizzas.add(new Pizza(3, "Ayurveda", 80d, Pizza.Type.VEGETERIAN));
    }

    @Override
    public Pizza getPizzaByID(Integer id) {
        Pizza result = null;
        for (Pizza pizza : pizzas) {
            if (pizza.getId().equals(id)) {
                result = pizza;
                break;
            }
        }
        return result;
    }
*/
  private Map<Integer, Pizza> pizzasDB;

    public InMemPizzaRepository() {
        this.pizzasDB = new HashMap<Integer, Pizza>();
    }
    public Map<Integer, Pizza> getPizzasDB() {
        return pizzasDB;
    }

    public void setPizzasDB(Map<Integer, Pizza> pizzasDB) {
        this.pizzasDB = pizzasDB;
    }

    @Benchmark
    public Pizza findById(Integer id) {
        return pizzasDB.get(id);
    }

    public Pizza saveOrUpdate(Pizza pizza) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Pizza> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Pizza pizza) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Pizza getPizzaByID(Integer id) {
        return null;
    }

    @Override
    public Pizza create(Pizza pizza) {
        return null;
    }


}
