package com.bkane56.grocerylist.items;


public class GroceryListItem {

    private int quantity;
    private String groceryItem;

    public GroceryListItem() {

    }

    public GroceryListItem(int quantity, String groceryItem) {
        this.quantity = quantity;
        this.groceryItem = groceryItem;
    }

    public String getQuantity() {
        return String.valueOf(quantity);
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getGroceryItem() {
        return groceryItem;
    }

    public void setGroceryItem(String title) {
        this.groceryItem = groceryItem;
    }
}

