package pizza.repository.pizza;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;
import pizzaservice.domain.pizza.Pizza;
import pizzaservice.repository.GenericRepository;
import pizzaservice.repository.pizza.InMemPizzaRepository;

public class InMemPizzaRepositoryTest {

    @Test
    public void getPizzaByIDNonException() throws Exception {
        GenericRepository<Pizza> repository = createBeanForPizzaRepository();
        Pizza result = repository.findById(1);
        Pizza nonExpected = null;
        assertThat(result, is(not(nonExpected)));
    }

    private  GenericRepository<Pizza> createBeanForPizzaRepository()
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        GenericRepository<Pizza>  repository = new InMemPizzaRepository();
        invokeInitMethod(repository);
        return repository;
    }

    private void invokeInitMethod( GenericRepository<Pizza> repository)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Method method = repository.getClass().getDeclaredMethod("init");
        method.setAccessible(true);
        method.invoke(repository);
    }

}
