package pl.karol.devicrent.components.device;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.karol.devicrent.components.category.Category;
import pl.karol.devicrent.components.category.CategoryRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Controller
public class DeviceController {

    private Scanner scanner;
    private DeviceRepository deviceRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    public DeviceController(Scanner scanner, DeviceRepository deviceRepository, CategoryRepository categoryRepository) {
        this.scanner = scanner;
        this.deviceRepository = deviceRepository;
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public void createDevice() {
        try {
            Device device = readDevice();
            deviceRepository.save(device);
            System.out.println("Added device");
            System.out.println(device);
        } catch (CategoryNotFoundException e) {
            System.err.println("Device can not be added " + e.getMessage());
        }
    }

    private Device readDevice() {
        Device device = new Device();
        System.out.println("Give device name:");
        device.setName(scanner.nextLine());
        System.out.println("Give device description:");
        device.setDescription(scanner.nextLine());
        System.out.println("Device price:");
        device.setPrice(scanner.nextDouble());
        System.out.println("Device quantity:");
        device.setQuantity(scanner.nextInt());
        scanner.nextLine();
        System.out.println("Device category(name)");
        String deviceCategoryName = scanner.nextLine();
        Optional<Category> category = categoryRepository.findByNameIgnoreCase(deviceCategoryName);
        category.ifPresentOrElse(device::setCategory,
                () -> {
                    throw new CategoryNotFoundException("Category with provided name doesn't exist");
                }
        );
        return device;
    }

    public void removeDevice() {
        System.out.println("Give device id you want to delete:");
        Long deviceId = scanner.nextLong();
        Optional<Device> device = deviceRepository.findById(deviceId);
        device.ifPresentOrElse(deviceRepository::delete,() -> System.out.println("There is no device with this ID"));
    }

    public void searchDevices() {
        System.out.println("Give text you want to search for:");
        String searchText = scanner.nextLine();
        List<Device> devices = deviceRepository.findAllByNameContainingIgnoreCase(searchText);
        if(devices.isEmpty())
            throw new DevicesNotFoundException("There is any devices matching search text");
        System.out.printf("Found %d devices:\n", devices.size());
        devices.forEach(System.out::println);
    }
}
