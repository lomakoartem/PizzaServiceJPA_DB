package pizzaservice.infrastructure;

import java.lang.reflect.*;
import java.util.Arrays;

public class BeanBuilder {

    private final Class<?> clazz;
    private ApplicationContext ac;
    private Object bean;
    private Object beanProxy;
    private boolean isProxyPresent;

    public BeanBuilder(Class<?> clazz, ApplicationContext ac) {
        this.clazz = clazz;
        this.ac = ac;
    }

    public void createBean()
            throws InstantiationException, IllegalAccessException, InvocationTargetException, Exception {
        Constructor<?> constructor = clazz.getConstructors()[0];

        if (constructor.getParameterCount() == 0) {
            bean = clazz.newInstance();
        } else {
            bean = createNewInstanceWithParams(constructor);
        }
    }

    void createBeanProxy() {
        Method[] methods = clazz.getMethods();
        boolean isProxyNeeded = false;
        for (Method method : methods) {
            if (method.getAnnotation(Benchmark.class) != null) {
                isProxyNeeded = true;
                break;
            }
        }

        if (!isProxyNeeded) {
            return;
        }
        isProxyPresent = true;
        ClassLoader loader = clazz.getClassLoader();
        Class<?>[] interfaces = clazz.getInterfaces();
        InvocationHandler h = new DynamicInvocationHandler(bean);
        Object proxyInstance = Proxy.newProxyInstance(loader, interfaces, h);
        beanProxy = proxyInstance;
    }

    static class DynamicInvocationHandler implements InvocationHandler {

        private Object obj;

        public DynamicInvocationHandler(Object obj) {
            this.obj = obj;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Benchmark benchmark = method.getAnnotation(Benchmark.class);
            if (benchmark == null || !benchmark.active()) {
                return method.invoke(obj, args);
            }
            long startTime = System.nanoTime();
            Object object = method.invoke(obj, args);
            long endTime = System.nanoTime();
            long totalTime = endTime - startTime;
            System.out.println("Invoked " + method.getName() + " on " + obj.getClass().getSimpleName() + " with args: "
                    + Arrays.toString(args) + ". Total time: " + totalTime);
            return object;
        }

    }

    public void callPostConstructMethod() throws Exception {
        Method[] methods = clazz.getMethods();
        for (Method m : methods) {
            if (m.getAnnotation(PostConstruct.class) != null) {
                m.invoke(bean);
            }
        }
    }

    public void callInitMethod() throws Exception {
        String methodName = "init";
        Method[] methods = clazz.getMethods();
        for (Method m : methods) {
            if (m.getName().equals(methodName)) {
                m.invoke(bean);
            }
        }
    }

    public Object build() {
        return bean;
    }

    private Object createNewInstanceWithParams(Constructor<?> constructor)
            throws Exception, InstantiationException, IllegalAccessException, InvocationTargetException {
        Object bean;
        Object[] paramBeans = getParams(constructor);
        bean = constructor.newInstance(paramBeans);
        return bean;
    }

    private Object[] getParams(Constructor<?> constructor) throws Exception {
        Class<?>[] paramTypes = constructor.getParameterTypes();
        Object[] paramBeans = new Object[paramTypes.length];
        for (int i = 0; i < paramTypes.length; ++i) {
            paramBeans[i] = getBeanByType(paramTypes[i]);
        }
        return paramBeans;
    }

    private Object getBeanByType(Class<?> paramType) throws Exception {
        String paramBeanName = getBeanNameByType(paramType);
        return ac.getBean(paramBeanName);
    }

    private String getBeanNameByType(Class<?> paramType) {
        String paramTypeName = paramType.getSimpleName();
        String paramBeanName = changeFirstLetterToLowercase(paramTypeName);
        return paramBeanName;
    }

    private String changeFirstLetterToLowercase(String paramTypeName) {
        String paramName = paramTypeName.substring(0, 1).toLowerCase() + paramTypeName.substring(1);
        return paramName;
    }

}
