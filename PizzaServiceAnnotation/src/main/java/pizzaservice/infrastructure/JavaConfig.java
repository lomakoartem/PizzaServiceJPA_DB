package pizzaservice.infrastructure;

import pizzaservice.repository.order.InMemOrderRepository;
import pizzaservice.repository.pizza.InMemPizzaRepository;
import pizzaservice.service.orderService.SimpleOrderService;

import java.util.HashMap;
import java.util.Map;


public class JavaConfig implements Config {

    private static Map<String, Class<?>> beans = new HashMap<>();
    {
        beans.put("orderRepository", InMemOrderRepository.class);
        beans.put("pizzaRepository", InMemPizzaRepository.class);
        beans.put("orderService", SimpleOrderService.class);
    }

    @Override
    public Class<?> getImpl(String bean) {
        return beans.get(bean);
    }

}
