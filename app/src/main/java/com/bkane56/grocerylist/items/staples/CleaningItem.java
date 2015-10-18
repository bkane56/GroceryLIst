package com.bkane56.grocerylist.items.staples;

import com.bkane56.grocerylist.items.StaplesListItem;

public class CleaningItem extends StaplesListItem {
    private boolean isChecked = false;
    private String staplesItem;
    private String stapleType = "Cleaning";
    private int isleOrder;


    public CleaningItem(){
        stapleType = "Cleaning";

    }
    public CleaningItem(String staplesItem){
        this.staplesItem = staplesItem;
    }

    public CleaningItem(String staplesItem, boolean isChecked) {
        this.staplesItem = staplesItem;
        this.isChecked = isChecked;
    }
}
