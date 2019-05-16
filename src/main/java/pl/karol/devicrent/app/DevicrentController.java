package pl.karol.devicrent.app;

import org.springframework.stereotype.Controller;
import pl.karol.devicrent.components.category.CategoryController;
import pl.karol.devicrent.components.customer.CustomerController;
import pl.karol.devicrent.components.device.DeviceController;
import pl.karol.devicrent.components.rent.RentController;

import java.util.Arrays;
import java.util.Scanner;

@Controller
public class DevicrentController {

    private Scanner scanner;
    private DeviceController deviceController;
    private CategoryController categoryController;
    private CustomerController customerController;
    private RentController rentController;

    public DevicrentController(Scanner scanner, DeviceController deviceController, CategoryController categoryController,
                                 CustomerController customerController, RentController rentController) {
        this.scanner = scanner;
        this.deviceController = deviceController;
        this.categoryController = categoryController;
        this.customerController = customerController;
        this.rentController = rentController;
    }

    public void mainLoop() {
        Options option;
        do {
            printOptions();
            option = getUserOption();
            executeOption(option);
        } while (option != Options.END);
    }

    private void printOptions() {
        System.out.println("Options:");
        Arrays.stream(Options.values())
                .forEach(System.out::println);
    }

    private Options getUserOption() {
        boolean correctOptionSelected = false;
        Options option = null;
        while (!correctOptionSelected) {
            System.out.println("Give option ID:");
            int userInput = scanner.nextInt();
            scanner.nextLine();
            try {
                option = Options.getOption(userInput);
                correctOptionSelected = true;
            } catch (InvalidOptionException e) {
                System.err.println(e.getMessage());
            }
        }
        return option;
    }

    private void executeOption(Options option) {
        switch (option) {
            case ADD_DEVICE:
                deviceController.createDevice();
                break;
            case ADD_CATEGORY:
                categoryController.createCategory();
                break;
            case ADD_CUSTOMER:
                customerController.createCustomer();
                break;
            case RENT_DEVICE:
                rentController.rentDeviceToCustomer();
                break;
            case DELETE_DEVICE:
                deviceController.removeDevice();
                break;
            case DELETE_CATEGORY:
                categoryController.removeCategory();
                break;
            case DELETE_CUSTOMER:
                customerController.removeCustomer();
                break;
            case SEARCH_DEVICES:
                deviceController.searchDevices();
                break;
            case END:
                close();
        }
    }


    private void close() {
        scanner.close();
        System.out.println("Bye bye!");
    }
}
