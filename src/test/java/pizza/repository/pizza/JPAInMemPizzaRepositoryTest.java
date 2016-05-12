package pizza.repository.pizza;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mysql.jdbc.Statement;
import pizzaservice.domain.pizza.Pizza;
import pizzaservice.repository.GenericRepository;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/resources/repositoryTestContext.xml"})
public class JPAInMemPizzaRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {


    private GenericRepository<Pizza> pizzaRepository;

    @Test
    public void testGetPizza() {
        final String sql = "INSERT INTO pizza (name, price, type) values ('sql pizza', '100', 'SEA')";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                return con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            }
        }, keyHolder);

        int id = keyHolder.getKey().intValue();

        Pizza pizza = pizzaRepository.findById(id);
        assertNotNull(pizza);
    }

    @Test
    public void testInsertPizzaAndGetPizzaById() {
        Pizza pizza = new Pizza(1,"Test pizza", 10.0, Pizza.Type.SEA);
        pizzaRepository.saveOrUpdate(pizza);
        Pizza expected = pizza;
        Pizza result = pizzaRepository.findById(pizza.getId());
        assertEquals(expected, result);
        assertEquals(expected.getName(), result.getName());
        assertEquals(expected.getPrice(), result.getPrice(), 0.001);
        assertEquals(expected.getType(), result.getType());
    }

    @Test
    public void testUpdatePizza(){
        Pizza pizza = new Pizza(1,"Before update", 10.0, Pizza.Type.SEA);
        pizzaRepository.saveOrUpdate(pizza);
        String updatedName = "After update";
        double updatedPrice = 200;
        Pizza.Type updatedType = Pizza.Type.VEGETERIAN;
        pizza.setName(updatedName);
        pizza.setPrice(updatedPrice);
        pizza.setType(updatedType);
        pizzaRepository.saveOrUpdate(pizza);
        Pizza expected = pizza;
        Pizza result = pizzaRepository.findById(pizza.getId());
        assertEquals(expected, result);
        assertEquals(expected.getName(), result.getName());
        assertEquals(expected.getPrice(), result.getPrice(), 0.0001);
        assertEquals(expected.getType(), result.getType());
    }


//    @Test
//    public void testDeletePizza() {
//        boolean expected = false;
//        boolean result = pizzaRepository.delete(pizzaRepository.findById(1));
//        assertEquals(expected, result);
//
//        Pizza pizza = new Pizza("Deleting pizza", 500, Pizza.PizzaType.SEA);
//        pizzaRepository.insert(pizza);
//        int pizzaId = pizza.getId();
//        expected = true;
//        result = pizzaRepository.delete(pizzaId);
//        assertEquals(expected, result);
//    }

}