package pizzaservice.infrastructure;

public interface Config {
    Class<?> getImpl(String bean);
}
