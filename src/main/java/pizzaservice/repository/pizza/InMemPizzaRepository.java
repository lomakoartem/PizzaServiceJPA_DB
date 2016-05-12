package pizzaservice.repository.pizza;

import pizzaservice.domain.pizza.Pizza;
import pizzaservice.infrastructure.Benchmark;
import pizzaservice.infrastructure.PostConstruct;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Repository
public class InMemPizzaRepository implements PizzaRepository {

    private Map<Integer, Pizza> pizzasDB;

    public InMemPizzaRepository() {
        this.pizzasDB = new HashMap<Integer, Pizza>();
    }        
//
   @PostConstruct
   public void init() {
       pizzasDB.put(1, new Pizza(1,"sea",10.0, Pizza.Type.SEA));
       pizzasDB.put(2, new Pizza(2,"meat" ,20.0, Pizza.Type.MEAT));
       pizzasDB.put(3, new Pizza(3, "vegeterian", 30.0, Pizza.Type.VEGETERIAN));
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
}
