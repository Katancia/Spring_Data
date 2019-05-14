package pl.karol.devicrent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.karol.devicrent.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
