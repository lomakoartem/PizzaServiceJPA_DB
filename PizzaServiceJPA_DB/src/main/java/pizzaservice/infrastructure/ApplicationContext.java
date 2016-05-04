package pizzaservice.infrastructure;

public interface ApplicationContext {

    Object getBean(String bean) throws Exception;

}
