package pl.karol.devicrent.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "device")
public class Device {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String name;
  @Column(length = 2048)
  private String description;
  private long quantity;
  private double price;
  @ManyToOne(cascade = CascadeType.PERSIST)
  @JoinColumn(name = "category_id")
  private Category category;
  @ManyToMany(cascade = CascadeType.PERSIST)
  @JoinTable(name = "device_customers",
    joinColumns = {@JoinColumn(name = "device_id", referencedColumnName = "id")},
    inverseJoinColumns = {@JoinColumn(name = "customer_id", referencedColumnName = "id")}
  )
  private List<Customer> customers = new ArrayList<>();

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

  public long getQuantity() {
    return quantity;
  }

  public void setQuantity(long quantity) {
    this.quantity = quantity;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public List<Customer> getCustomers() {
    return customers;
  }

  public void setCustomers(List<Customer> customers) {
    this.customers = customers;
  }

  public void addCustomer(Customer customer) {
    customers.add(customer);
    customer.getRentDevices().add(this);
  }

  @Override
  public String toString() {
    return "Device{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", description='" + description + '\'' +
            ", quantity=" + quantity +
            ", price=" + price +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Device device = (Device) o;
    return id == device.id &&
            quantity == device.quantity &&
            Double.compare(device.price, price) == 0 &&
            Objects.equals(name, device.name) &&
            Objects.equals(description, device.description) &&
            Objects.equals(category, device.category) &&
            Objects.equals(customers, device.customers);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, description, quantity, price, category, customers);
  }
}
