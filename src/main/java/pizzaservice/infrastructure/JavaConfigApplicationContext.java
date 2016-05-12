package pizzaservice.infrastructure;

import java.util.HashMap;
import java.util.Map;

public class JavaConfigApplicationContext implements ApplicationContext {

    private Config config = new JavaConfig();
    private final Map<String, Object> context = new HashMap<>();

    @Override
    public Object getBean(String beanName) throws Exception {
        if (context.containsKey(beanName)) {
            return context.get(beanName);
        }
        Class<?> clazz = config.getImpl(beanName);
        if (clazz == null) {
            throw new RuntimeException("Bean not found");
        }
        Object bean = null;
        BeanBuilder builder = new BeanBuilder(clazz, this);
        builder.createBean();
        builder.createBeanProxy();
        builder.callPostConstructMethod();
        builder.callInitMethod();
        bean = builder.build();
        context.put(beanName, bean);
        return bean;
    }

}
