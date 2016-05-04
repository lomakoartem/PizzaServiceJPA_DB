package pizzaservice.domain.order;

import org.springframework.stereotype.Component;
import pizzaservice.domain.pizza.Pizza;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by lomak on 13.04.2016.
 */
@Component
@Entity
@Table(name = "order_details")
public class OrderComponent implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_detail_id")
    private Integer id;
    @OneToOne
    @JoinColumn(name = "pizza_id")
    private Pizza pizza;
    @Column(name = "pizza_quantity")
    private Integer quantity;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public OrderComponent() {
    }

    public OrderComponent(Pizza pizza, Integer quantity, Order order) {
        this.pizza = pizza;
        this.quantity = quantity;
        this.order = order;
    }

    public OrderComponent(Integer id, Pizza pizza, Integer quantity, Order order) {
        this.id = id;
        this.pizza = pizza;
        this.quantity = quantity;
        this.order = order;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "OrderDetails{" + "id=" + id + ", pizza=" + pizza + ", quantity=" + quantity + '}';
    }

}