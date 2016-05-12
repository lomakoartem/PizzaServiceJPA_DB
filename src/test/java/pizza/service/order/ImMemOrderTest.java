package pizza.service.order;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import pizzaservice.domain.address.Address;
import pizzaservice.domain.customer.Customer;
import pizzaservice.domain.order.Order;
import pizzaservice.domain.pizza.Pizza;
import pizzaservice.service.order.OrderService;
import pizzaservice.service.pizza.PizzaService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by lomak on 12.05.2016.
 */
public class ImMemOrderTest extends AbstractTransactionalJUnit4SpringContextTests {
    private Pizza pizza = new Pizza(1, "Pizza 1", 100.0, Pizza.Type.SEA);

    private Address address = new Address(1,"Kiev");

    private Customer customer = new Customer("Artem");

    private Order newOrder= new Order(customer, Order.OrderStatus.NEW,0);

    @Autowired
    private OrderService orderService;

    @Autowired
    private PizzaService pizzaService;

    @Before
    public void initMethod() {
        pizzaService.saveOrUpdate(pizza);

    }

    @Test
    public void testPlaceNewOrder() {

        orderService.addPizzaToOrder(newOrder, pizza);
        List<Pizza> pizzas = getOrderPizzasFromDatabase(newOrder);

        int expectedPizzasNumber = 1;
        int resultPizzasNumber = pizzas.size();
        assertEquals(expectedPizzasNumber, resultPizzasNumber);
    }


    private List<Pizza> getOrderPizzasFromDatabase(Order order) {
        String sql = "SELECT p.name, p.price, p.type FROM pizza p INNER JOIN order_pizza op ON p.pizza_id = op.pizza_id WHERE op.order_id = " + Integer.toString(order.getId());
        List<Pizza> pizzas = this.jdbcTemplate.query(sql, new RowMapper<Pizza>() {

            public Pizza mapRow(ResultSet rs, int rowNum) throws SQLException {
                Pizza pizza = new Pizza();
                pizza.setName(rs.getString("name"));
                pizza.setPrice(rs.getDouble("price"));
                pizza.setType(Pizza.Type.valueOf(rs.getString("type").toUpperCase()));
                return pizza;
            }

        });
        return pizzas;
    }

}