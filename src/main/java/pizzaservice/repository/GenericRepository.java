
package pizzaservice.repository;

import java.util.List;

public interface GenericRepository<T> {
    T findById(Integer id);
    T saveOrUpdate(T entity);
    List<T> findAll();
    void delete(T entity);
}
