package com.bkane56.grocerylist.items;


public class StaplesListItem {

    private boolean isChecked = false;
    private String staplesItem;
    private String stapleType = "Basic";
    private int isleOrder;

    public StaplesListItem() {

    }

    public StaplesListItem(String staplesItem){
        this.staplesItem = staplesItem;
    }

    public StaplesListItem(String staplesItem, boolean isChecked) {
        this.staplesItem = staplesItem;
        this.isChecked = isChecked;
    }

    public String getStapleType() {
        return stapleType;
    }

    public void setStapleType(String stapleType) {
        this.stapleType = stapleType;
    }

    public String getStaplesItem() {
        return staplesItem;
    }

    public void setStaplesItem(String title) {
        this.staplesItem = staplesItem;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    public int getIsleOrder() {
        return isleOrder;
    }

    public void setIsleOrder(int isleOrder) {
        this.isleOrder = isleOrder;
    }
}
