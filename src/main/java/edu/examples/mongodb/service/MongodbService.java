package edu.examples.mongodb.service;

public interface MongodbService {

    // CRUD operations

    //CREATE
    void createGroceryItems();

    // READ
    // 1. Show all the data
    void showAllGroceryItems();

    // 2. Get item by name
    void getGroceryItemByName(String name);

    // 3. Get name and items of all items of a particular category
    void getItemsByCategory(String category);

    // 4. Get count of documents in the collection
    void findCountOfGroceryItems();

    // UPDATE APPROACH 1: Using MongoRepository
    void updateCategoryName(String category);

    // UPDATE APPROACH 2: Using MongoTemplate
    public void updateItemQuantity(String name, float newQuantity);

    // DELETE
    public void deleteGroceryItem(String id);
}

