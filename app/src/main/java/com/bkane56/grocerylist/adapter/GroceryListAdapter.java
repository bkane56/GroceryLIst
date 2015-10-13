package com.bkane56.grocerylist.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bkane56.grocerylist.items.GroceryListItem;
import com.bkane56.grocerylist.R;

import java.util.Collections;
import java.util.List;

public class GroceryListAdapter extends RecyclerView.Adapter<GroceryListAdapter.MyViewHolder> {


    List<GroceryListItem> data = Collections.emptyList();
    private LayoutInflater inflater;
    private Context context;

    public GroceryListAdapter(Context context, List<GroceryListItem> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    public void delete(int position) {
        data.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.grocery_list_recycler_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        GroceryListItem current = data.get(position);
        holder.item.setText(current.getGroceryItem());
        if(current.getQuantity().equals("1")){
            holder.qty.setText(" ");
        }else{
            holder.qty.setText(current.getQuantity());
        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView item;
        TextView qty;

        public MyViewHolder(View itemView) {
            super(itemView);
            item = (TextView) itemView.findViewById(R.id.itemName);
            qty = (TextView) itemView.findViewById(R.id.qty);
        }
    }
}
