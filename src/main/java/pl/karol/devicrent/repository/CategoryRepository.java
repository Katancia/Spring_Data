package pl.karol.devicrent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.karol.devicrent.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}