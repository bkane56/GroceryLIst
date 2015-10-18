package com.bkane56.grocerylist.items.staples;

import com.bkane56.grocerylist.items.StaplesListItem;

public class PersonalCareItem extends StaplesListItem{
    private boolean isChecked = false;
    private String staplesItem;
    private String stapleType = "Personal";
    private int isleOrder;


    public PersonalCareItem(){
        stapleType = "Personal";

    }
    public PersonalCareItem(String staplesItem){
        this.staplesItem = staplesItem;
    }

    public PersonalCareItem(String staplesItem, boolean isChecked) {
        this.staplesItem = staplesItem;
        this.isChecked = isChecked;
    }
}
