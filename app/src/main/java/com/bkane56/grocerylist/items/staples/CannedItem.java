package com.bkane56.grocerylist.items.staples;

import com.bkane56.grocerylist.items.StaplesListItem;


public class CannedItem extends StaplesListItem {

    private boolean isChecked = false;
    private String staplesItem;
    private String stapleType = "Canned";
    private int isleOrder;


    public CannedItem(){
        stapleType = "Canned";
    }
    public CannedItem(String staplesItem){
        this.staplesItem = staplesItem;
    }

    public CannedItem(String staplesItem, boolean isChecked) {
        this.staplesItem = staplesItem;
        this.isChecked = isChecked;
    }
}
