package pizzaservice.domain.calculator;

import org.springframework.stereotype.Component;
import pizzaservice.domain.order.Order;
import pizzaservice.domain.order.OrderComponent;

/**
 * Created by lomak on 02.05.2016.
 */
@Component
public class Calculator {

    private static final int ORDER_SIZE_THRESHOLD = 4;
    private static final double DISCOUNT_FOR_MOST_EXPENSIVE_PIZZA = 0.3;
    private static final double UPPER_BOUND_FOR_BONUSCARD_DISCOUNT = 0.3;

    public Calculator() {
    }

    public Double calculatePrice(Order order) {
        Double result;
        result = calcMostExpensPizzaDisc(order);
        result = calcDiscountFromBonusCard(order, result);
        return result;
    }

    private Double calcMostExpensPizzaDisc(Order order) {
        if (order.getOrderSize() > ORDER_SIZE_THRESHOLD) {
            Double maxPrice = defineMaxPricePizza(order);
            return calcPurePizzasPrice(order) - maxPrice
                    * DISCOUNT_FOR_MOST_EXPENSIVE_PIZZA;
        } else {
            return calcPurePizzasPrice(order);
        }
    }

    private Double defineMaxPricePizza(Order order) {
        Double maxPrice = 0d;
        for (OrderComponent od : order.getPizzas()) {
            Double pizzaPrice = od.getPizza().getPrice();
            if (pizzaPrice > maxPrice) {
                maxPrice = pizzaPrice;
            }
        }
        return maxPrice;
    }

    private Double calcPurePizzasPrice(Order order) {
        Double purePrice = 0d;
        for(OrderComponent od : order.getPizzas()) {
            purePrice += od.getPizza().getPrice() * od.getQuantity();
        }
        return purePrice;
    }

    private Double calcDiscountFromBonusCard(Order order, Double currPrice) {
        Double bonusSize = order.getCustomer().getBonusCard().getBonusSize();
        if (bonusSize < currPrice * UPPER_BOUND_FOR_BONUSCARD_DISCOUNT) {
            return currPrice - bonusSize;
        } else {
            return currPrice * UPPER_BOUND_FOR_BONUSCARD_DISCOUNT;
        }
    }

}