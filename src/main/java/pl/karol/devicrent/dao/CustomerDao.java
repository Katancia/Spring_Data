package pl.karol.devicrent.dao;

import org.springframework.stereotype.Repository;
import pl.karol.devicrent.entity.Customer;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public class CustomerDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void save(Customer customer) {
        entityManager.persist(customer);
    }

    public Customer get(Long id) {
        return entityManager.find(Customer.class, id);
    }

    @Transactional
    void update(Customer customer) {
        entityManager.merge(customer);
    }

    @Transactional
    void remove(Long customerId) {
        Customer objToRemove = get(customerId);
        entityManager.remove(objToRemove);
    }
}
