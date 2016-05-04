package pizzaservice;


import pizzaservice.domain.customer.Customer;
import pizzaservice.domain.order.Order;
import pizzaservice.infrastructure.ApplicationContext;
import pizzaservice.infrastructure.JavaConfigApplicationContext;
import pizzaservice.service.orderService.OrderService;

public class PizzaApp {

    public static void main(String[] args) throws Exception {
        //Customer customer = new Customer(1, "Peter Stromare");
        Order order;

        ApplicationContext ac = new JavaConfigApplicationContext();
        /*PizzaRepository pizzaRepository = (PizzaRepository) ac.getBean("pizzaRepository");
        System.out.println(pizzaRepository.getPizzaByID(1));*/

        OrderService orderService = (OrderService) ac.getBean("orderService");
      //  order = orderService.placeNewOrder(customer, 1, 2, 3);

        //System.out.println(order);

    }

}
