package pl.karol.devicrent.components.category;

import pl.karol.devicrent.components.device.Device;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "category")
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String name;
  @Column(length = 1024)
  private String description;
  @OneToMany(mappedBy = "category")
  private Set<Device> device = new HashSet<>();

  public Category() {
  }

  public Category(String name, String description) {
    this.name = name;
    this.description = description;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public String toString() {
    return "Category{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", description='" + description + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Category category = (Category) o;
    return id == category.id &&
            Objects.equals(name, category.name) &&
            Objects.equals(description, category.description) &&
            Objects.equals(device, category.device);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, description, device);
  }
}
