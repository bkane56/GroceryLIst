package com.bkane56.grocerylist.items.staples;

import com.bkane56.grocerylist.items.StaplesListItem;

public class ProduceItem extends StaplesListItem {

    private boolean isChecked = false;
    private String staplesItem;
    private String stapleType = "Produce";
    private int isleOrder;


    public ProduceItem(){
        stapleType = "Produce";

    }
    public ProduceItem(String staplesItem){
        this.staplesItem = staplesItem;
    }

    public ProduceItem(String staplesItem, boolean isChecked) {
        this.staplesItem = staplesItem;
        this.isChecked = isChecked;
    }
}
