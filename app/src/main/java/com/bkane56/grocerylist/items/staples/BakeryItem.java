package com.bkane56.grocerylist.items.staples;


import com.bkane56.grocerylist.items.StaplesListItem;

public class BakeryItem extends StaplesListItem{
    private boolean isChecked = false;
    private String staplesItem;
    private String stapleType = "Bakery";
    private int isleOrder;

    public BakeryItem(){
        stapleType = "Bakery";

    }
    public BakeryItem(String staplesItem){
        this.staplesItem = staplesItem;
    }

    public BakeryItem(String staplesItem, boolean isChecked) {
        this.staplesItem = staplesItem;
        this.isChecked = isChecked;
    }
}
