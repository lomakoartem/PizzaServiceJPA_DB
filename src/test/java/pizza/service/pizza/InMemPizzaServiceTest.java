package pizza.service.pizza;

import static org.junit.Assert.*;

import java.sql.*;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.mysql.jdbc.Statement;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import pizzaservice.domain.pizza.Pizza;
import pizzaservice.service.pizza.PizzaService;

/**
 * Created by lomak on 12.05.2016.
 */
public class InMemPizzaServiceTest extends AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
    private PizzaService pizzaService;

    @Test
    public void testgetAll() {
        insertGetId();
        insertGetId();
        insertGetId();
        int expected = 3;
        int result = pizzaService.findAll().size();
        assertEquals(expected, result);
    }

    @Test
    public void testInsertPizza() {
        Pizza pizza1 = new Pizza(1, "Pizza 1", 10.0, Pizza.Type.SEA);
        Pizza pizza2 = new Pizza(2, "Pizza 2", 20.0, Pizza.Type.MEAT);
        Pizza pizza3 = new Pizza(3, "Pizza 3", 30.0, Pizza.Type.SEA);
        pizzaService.saveOrUpdate(pizza1);
        pizzaService.saveOrUpdate(pizza2);
        pizzaService.saveOrUpdate(pizza3);
        int expected = 3;
        int result = pizzaService.findAll().size();
        assertEquals(expected, result);
    }

    @Test
    public void testUpdatePizza() {
        Pizza pizza = new Pizza(1,"Pizza 1", 10.0, Pizza.Type.SEA);
        pizzaService.saveOrUpdate(pizza);
        String newName = "Update";
        double newPrice = 100500;
        Pizza.Type newType = Pizza.Type.MEAT;
        pizza.setName(newName);
        pizza.setPrice(newPrice);
        pizza.setType(newType);
        pizzaService.saveOrUpdate(pizza);
        Pizza result = getPizzaFromDatabase(pizza.getId());
        assertEquals(newName, result.getName());
        assertEquals(newPrice, result.getPrice(), 0.0001);
        assertEquals(newType, result.getType());
    }



    private int insertGetId() {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                return con.prepareStatement("INSERT INTO pizza (name, price, type) VALUES ('Pizza 1', 100, 'SEA')", Statement.RETURN_GENERATED_KEYS);
            }
        }, keyHolder);
        return keyHolder.getKey().intValue();
    }

    private List<Pizza> getAllPizzasFromDatabase() {
        String sql = "SELECT name, price, type FROM pizza";
        List<Pizza> pizzas = this.jdbcTemplate.query(sql, new RowMapper<Pizza>() {

            @Override
            public Pizza mapRow(ResultSet rs, int rowNum)
                    throws SQLException {
                Pizza pizza = new Pizza();
                pizza.setName(rs.getString("name"));
                pizza.setPrice(rs.getDouble("price"));
                pizza.setType(Pizza.Type.valueOf(rs.getString("type").toUpperCase()));
                return pizza;
            }

        });
        return pizzas;
    }

    private Pizza getPizzaFromDatabase(Integer pizzaid) {
        return this.jdbcTemplate.queryForObject("SELECT name, price, type FROM pizza WHERE pizza_id = ?", new Object[]{pizzaid}, new RowMapper<Pizza>() {

            @Override
            public Pizza mapRow(ResultSet rs, int rowNum)
                    throws SQLException {
                Pizza pizza = new Pizza();
                pizza.setName(rs.getString("name"));
                pizza.setPrice(rs.getDouble("price"));
                pizza.setType(Pizza.Type.valueOf(rs.getString("type").toUpperCase()));
                return pizza;
            }

        });
    }

}