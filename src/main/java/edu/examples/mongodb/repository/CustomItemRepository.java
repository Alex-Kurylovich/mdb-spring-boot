package edu.examples.mongodb.repository;

public interface CustomItemRepository {

    void updateItemQuantity(String itemName, float newQuantity);
}
