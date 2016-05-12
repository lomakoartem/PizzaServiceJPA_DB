package pizzaservice.domain.pizza;
import javax.persistence.*;

@Entity
@Table(name = "pizza")
public class Pizza implements Comparable<Pizza> {


	public enum Type {
		VEGETERIAN, SEA, MEAT
	}
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	@Column(name = "pizza_ID")
	private Integer id;
	@Column(name = "name" )
	private String name;
	@Column(name = "price" )
	private Double price;
	@Enumerated(EnumType.STRING)
	private Type type;

	public Pizza() {
	}

	public Pizza(Integer id, String name, Double price, Type type) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.type = type;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Pizza [id=" + id + ", name=" + name + ", price=" + price + ", type=" + type + "]";
	}

	@Override
	public int compareTo(Pizza pizza) {
		if(this.getId() < pizza.getId()) {
			return -1;
		} else if (this.getId() > pizza.getId()){
			return 1;
		}
		return 0;

	}

}
