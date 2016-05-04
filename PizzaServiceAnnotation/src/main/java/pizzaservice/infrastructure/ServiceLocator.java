package pizzaservice.infrastructure;

public class ServiceLocator {
    private static final ServiceLocator instance = new ServiceLocator();

    public static ServiceLocator getInstance() {
        return instance;
    }

    private Config config = new JavaConfig();

    private ServiceLocator() {
    }

    public Object lookup(String bean) {
        Class<?> clazz = config.getImpl(bean);
        if (clazz == null) {
            throw new RuntimeException("Bean not found");
        }
        try {
            return clazz.newInstance();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

}
