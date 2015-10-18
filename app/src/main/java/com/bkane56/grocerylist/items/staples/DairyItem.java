package com.bkane56.grocerylist.items.staples;

import com.bkane56.grocerylist.items.StaplesListItem;

public class DairyItem extends StaplesListItem{
    private boolean isChecked = false;
    private String staplesItem;
    private String stapleType = "Dairy";
    private int isleOrder;


    public DairyItem(){
        stapleType = "Dairy";

    }
    public DairyItem(String staplesItem){
        this.staplesItem = staplesItem;
    }

    public DairyItem(String staplesItem, boolean isChecked) {
        this.staplesItem = staplesItem;
        this.isChecked = isChecked;
    }
}
