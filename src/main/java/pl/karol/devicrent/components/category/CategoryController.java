package pl.karol.devicrent.components.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Optional;
import java.util.Scanner;

@Controller
public class CategoryController {

    private Scanner scanner;
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryController(Scanner scanner, CategoryRepository categoryRepository) {
        this.scanner = scanner;
        this.categoryRepository = categoryRepository;
    }

    public void createCategory() {
        Category category = readCategory();
        categoryRepository.save(category);
        System.out.println("Added category");
        System.out.println(category);
    }

    private Category readCategory() {
        Category category = new Category();
        System.out.println("Give category name:");
        category.setName(scanner.nextLine());
        System.out.println("Give category description:");
        category.setDescription(scanner.nextLine());
        return category;
    }
    public void removeCategory() {
        System.out.println("Give category id you want to delete:");
        Long categoryId = scanner.nextLong();
        Optional<Category> category = categoryRepository.findById(categoryId);
        category.ifPresentOrElse(categoryRepository::delete,() -> System.out.println("There is no category with this ID"));
    }
}
