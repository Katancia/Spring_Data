package pl.karol.devicrent.dao;

import org.springframework.stereotype.Repository;
import pl.karol.devicrent.entity.Device;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public class DeviceDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void save(Device device) {
        entityManager.persist(device);
    }

    public Device get(Long id) {
        return entityManager.find(Device.class, id);
    }

    @Transactional
    void update(Device device) {
        entityManager.merge(device);
    }

    @Transactional
    void remove(Long deviceId) {
        Device objToRemove = get(deviceId);
        entityManager.remove(objToRemove);
    }
}
