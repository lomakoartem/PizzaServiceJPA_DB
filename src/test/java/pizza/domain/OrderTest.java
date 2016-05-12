package pizza.domain;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import pizzaservice.domain.order.Order;
import pizzaservice.domain.order.OrderComponent;
import pizzaservice.domain.pizza.Pizza;

import java.math.BigDecimal;

/**
 * Created by lomak on 12.05.2016.
 */
public class OrderTest {

    @Test(expected = NullPointerException.class)
    public void testAddOnePizza_whenPizzaIsNull_shouldThrowAnException() throws Exception {
        Order order = new Order();
        order.addPizza((Pizza) null);
    }

    @Test(expected = RuntimeException.class)
    public void testAddOnePizza_whenOrderHas10Pizzas_shouldThrowAnException() throws Exception {
        Order order = new Order();
        Pizza pizza = new Pizza();
       order.addPizza(pizza);
    }

   @Test
    public void testAddOnePizza_whenAddPizza_shouldAddItToList() throws Exception {

       OrderComponent orderComponent = new OrderComponent() ;
        Pizza pizza = new Pizza(1,"Meat" ,100.9, Pizza.Type.MEAT);
       orderComponent.setPizza(pizza);
       Pizza contains = orderComponent.getPizza();
       assertEquals(pizza, contains);
   }

}