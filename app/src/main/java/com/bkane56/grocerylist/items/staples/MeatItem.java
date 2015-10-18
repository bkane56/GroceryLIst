package com.bkane56.grocerylist.items.staples;


import com.bkane56.grocerylist.items.StaplesListItem;

public class MeatItem extends StaplesListItem {

    private boolean isChecked = false;
    private String staplesItem;
    private String stapleType = "Meat";
    private int isleOrder;


    public MeatItem(){
        stapleType = "Meat";

    }
    public MeatItem(String staplesItem){
        this.staplesItem = staplesItem;
    }

    public MeatItem(String staplesItem, boolean isChecked) {
        this.staplesItem = staplesItem;
        this.isChecked = isChecked;
    }
}
