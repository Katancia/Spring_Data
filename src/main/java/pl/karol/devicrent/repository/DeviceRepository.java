package pl.karol.devicrent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.karol.devicrent.entity.Device;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {
}
