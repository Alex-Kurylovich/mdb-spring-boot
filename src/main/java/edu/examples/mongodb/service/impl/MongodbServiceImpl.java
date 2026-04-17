package edu.examples.mongodb.service.impl;

import edu.examples.mongodb.model.GroceryItem;
import edu.examples.mongodb.repository.CustomItemRepository;
import edu.examples.mongodb.repository.ItemRepository;
import edu.examples.mongodb.service.MongodbService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MongodbServiceImpl implements MongodbService {

    final
    CustomItemRepository customItemRepository;
    final
    ItemRepository groceryItemRepo;
    final
    CustomItemRepository customRepo;

    List<GroceryItem> itemList = new ArrayList<GroceryItem>();

    public MongodbServiceImpl(CustomItemRepository customItemRepository, ItemRepository groceryItemRepo, CustomItemRepository customRepo) {
        this.customItemRepository = customItemRepository;
        this.groceryItemRepo = groceryItemRepo;
        this.customRepo = customRepo;
    }

    public String getItemDetails(GroceryItem item) {
        System.out.println(
                "Item Name: " + item.getName() +
                        ", \nItem Quantity: " + item.getItemQuantity() +
                        ", \nItem Category: " + item.getCategory()
        );
        return "";
    }

    @Override
    public void createGroceryItems() {
        System.out.println("Data creation started...");
        groceryItemRepo.save(new GroceryItem("Whole Wheat Biscuit", "Whole Wheat Biscuit", 5, "snacks"));
        groceryItemRepo.save(new GroceryItem("Kodo Millet", "XYZ Kodo Millet healthy", 2, "millets"));
        groceryItemRepo.save(new GroceryItem("Dried Red Chilli", "Dried Whole Red Chilli", 2, "spices"));
        groceryItemRepo.save(new GroceryItem("Pearl Millet", "Healthy Pearl Millet", 1, "millets"));
        groceryItemRepo.save(new GroceryItem("Cheese Crackers", "Bonny Cheese Crackers Plain", 6, "snacks"));
        System.out.println("Data creation complete...");
    }

    @Override
    public void showAllGroceryItems() {
        itemList = groceryItemRepo.findAll();
        itemList.forEach(item -> System.out.println(getItemDetails(item)));
    }

    @Override
    public void getGroceryItemByName(String name) {
        System.out.println("Getting item by name: " + name);
        GroceryItem item = groceryItemRepo.findItemByName(name);
        System.out.println(getItemDetails(item));
    }

    @Override
    public void getItemsByCategory(String category) {
        System.out.println("Getting items for the category " + category);
        List<GroceryItem> list = groceryItemRepo.findAll(category);
        list.forEach(item -> System.out.println("Name: " + item.getName() + ", Quantity: " + item.getItemQuantity()));
    }

    @Override
    public void findCountOfGroceryItems() {
        long count = groceryItemRepo.count();
        System.out.println("Number of documents in the collection = " + count);
    }

    @Override
    public void updateCategoryName(String category) {
        // Change to this new value
        String newCategory = "munchies";
        // Find all the items with the category
        List<GroceryItem> list = groceryItemRepo.findAll(category);
        list.forEach(item -> {
            // Update the category in each document
            item.setCategory(newCategory);
        });
        // Save all the items in database
        List<GroceryItem> itemsUpdated = groceryItemRepo.saveAll(list);
        if(itemsUpdated != null)
            System.out.println("Successfully updated " + itemsUpdated.size() + " items.");
    }

    @Override
    public void updateItemQuantity(String name, float newQuantity) {
        System.out.println("Updating quantity for " + name);
        customRepo.updateItemQuantity(name, newQuantity);
    }

    @Override
    public void deleteGroceryItem(String id) {
        groceryItemRepo.deleteById(id);
        System.out.println("Item with id " + id + " deleted...");
    }
}
