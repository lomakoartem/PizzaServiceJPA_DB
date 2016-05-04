package pizzaservice.domain.customer;
import org.springframework.beans.factory.annotation.Autowired;
import pizzaservice.domain.address.Address;
import pizzaservice.domain.card.Card;
import pizzaservice.domain.order.Order;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "customers")

public class Customer implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer_id")
	private Integer id;

	@OneToOne(orphanRemoval = true, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "card_id")
	private Card bonusCard;
	@OneToMany(mappedBy = "customer", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private List<Order> orders = new ArrayList<>();

	@ManyToMany(cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
	@JoinTable(name = "customer_address", joinColumns =  @JoinColumn(name = "customer_id"), inverseJoinColumns = @JoinColumn(name = "address_id"))
	private Set<Address> addresses;

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Customer() {
	}

	public Customer(Integer id, Card bonusCard,
					List<Order> orders) {
		this.id = id;
		this.bonusCard = bonusCard;
		this.orders = orders;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public Card getBonusCard() {
		return bonusCard;
	}

	public void setBonusCard(Card bonusCard) {
		this.bonusCard = bonusCard;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder("");
		for(Order o: orders) {
			str.append(o.toString());
		}
		return "Customer{" + "id=" + id + ", account=" +
				", bonusCard=" + bonusCard + ", orders=" + str +'}';
	}

}