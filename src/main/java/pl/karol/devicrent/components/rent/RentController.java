package pl.karol.devicrent.components.rent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.karol.devicrent.components.customer.Customer;
import pl.karol.devicrent.components.customer.CustomerRepository;
import pl.karol.devicrent.components.device.Device;
import pl.karol.devicrent.components.device.DeviceRepository;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Scanner;

@Controller
public class RentController {

    private Scanner scanner;
    private DeviceRepository deviceRepository;
    private CustomerRepository customerRepository;

    @Autowired
    public RentController(Scanner scanner, DeviceRepository deviceRepository, CustomerRepository customerRepository) {
        this.scanner = scanner;
        this.deviceRepository = deviceRepository;
        this.customerRepository = customerRepository;
    }

    @Transactional
    public void rentDeviceToCustomer() {
        try {
            rent();
        } catch (RentException e) {
            System.err.println(e.getMessage());
        }
    }

    private void rent() {
        System.out.println("Give customer pesel");
        String customerPesel = scanner.nextLine();
        System.out.println("Give device id");
        Long deviceId = scanner.nextLong();
        Optional<Customer> customer = customerRepository.findByPesel(customerPesel);
        Optional<Device> device = deviceRepository.findById(deviceId);
        if(customer.isPresent())
            device.ifPresentOrElse(dev -> {
                if(dev.getQuantity() > dev.getCustomers().size())
                    dev.addCustomer(customer.get());
                else
                    throw new RentException("There is no free devices left with provided ID");
            },() -> {
                throw new RentException("There is no device with this ID");
            });
        else
            throw new RentException("There is no Customer with this ID");
    }
}
