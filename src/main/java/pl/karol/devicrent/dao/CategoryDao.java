package pl.karol.devicrent.dao;

import org.springframework.stereotype.Repository;
import pl.karol.devicrent.entity.Category;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public class CategoryDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void save(Category device) {
        entityManager.persist(device);
    }

    public Category get(Long id) {
        return entityManager.find(Category.class, id);
    }

    @Transactional
    void update(Category category) {
        entityManager.merge(category);
    }

    @Transactional
    void remove(Long categoryId) {
        Category objToRemove = get(categoryId);
        entityManager.remove(objToRemove);
    }
}
