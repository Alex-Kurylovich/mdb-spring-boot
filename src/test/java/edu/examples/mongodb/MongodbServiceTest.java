package edu.examples.mongodb;

import edu.examples.mongodb.model.GroceryItem;
import edu.examples.mongodb.repository.CustomItemRepository;
import edu.examples.mongodb.repository.ItemRepository;
import edu.examples.mongodb.service.MongodbService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
@TestPropertySource(locations = {"classpath:test.properties"})
public class MongodbServiceTest {

    @Autowired
    private ItemRepository groceryItemRepo;

    @Autowired
    private MongodbService mongodbService;

    @Autowired
    CustomItemRepository customRepo;

    List<GroceryItem> itemList = new ArrayList<GroceryItem>();

    @Test
    public void testCreateGroceryItems() throws Exception {
        System.out.println("Data creation started...");

        groceryItemRepo.save(new GroceryItem("Whole Wheat Biscuit", "Whole Wheat Biscuit", 5, "snacks"));
        groceryItemRepo.save(new GroceryItem("Kodo Millet", "XYZ Kodo Millet healthy", 2, "millets"));
        groceryItemRepo.save(new GroceryItem("Dried Red Chilli", "Dried Whole Red Chilli", 2, "spices"));
        groceryItemRepo.save(new GroceryItem("Pearl Millet", "Healthy Pearl Millet", 1, "millets"));
        groceryItemRepo.save(new GroceryItem("Cheese Crackers", "Bonny Cheese Crackers Plain", 6, "snacks"));

        System.out.println("Data creation complete...");
    }

    @Test
    public void showAllGroceryItems() {
        itemList = groceryItemRepo.findAll();
        itemList.forEach(item -> System.out.println(getItemDetails(item)));
    }

    @Test
    public void getGroceryItemByName() {
        var name = "Whole Wheat Biscuit";
        System.out.println("Getting item by name: " + name);
        GroceryItem item = groceryItemRepo.findItemByName(name);
        System.out.println(getItemDetails(item));
    }

    @Test
    public void getItemsByCategory() {
        var category = "millets";
        System.out.println("Getting items for the category " + category);
        List<GroceryItem> list = groceryItemRepo.findAll(category);
        list.forEach(item -> System.out.println("Name: " + item.getName() + ", Quantity: " + item.getItemQuantity()));
    }

    @Test
    public void updateCategoryName() {
        var category = "snacks";
        // Change to this new value
        String newCategory = "munchies";
        // Find all the items with the category
        List<GroceryItem> list = groceryItemRepo.findAll(category);
        list.forEach(item -> {
            // Update the category in each document
            item.setCategory(newCategory);
        });
    }

    @Test
    public void updateItemQuantity() {
        var name = "Bonny Cheese Crackers Plain";
        var newQuantity = 10;
        System.out.println("Updating quantity for " + name);
        customRepo.updateItemQuantity(name, newQuantity);
    }

    @Test
    public void deleteGroceryItem() {
        var id = "Kodo Millet";
        groceryItemRepo.deleteById(id);
        System.out.println("Item with id " + id + " deleted...");
    }

    @Test
    public void findCountOfGroceryItems() {
        long count = groceryItemRepo.count();
        System.out.println("Number of documents in the collection = " + count);
    }

    public String getItemDetails(GroceryItem item) {
        System.out.println(
                "Item Name: " + item.getName() +
                        ", \nItem Quantity: " + item.getItemQuantity() +
                        ", \nItem Category: " + item.getCategory()
        );
        return "";
    }
}
