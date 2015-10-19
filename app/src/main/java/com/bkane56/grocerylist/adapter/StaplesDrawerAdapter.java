package com.bkane56.grocerylist.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bkane56.grocerylist.R;
import com.bkane56.grocerylist.items.StaplesDrawerItem;

import java.util.Collections;
import java.util.List;


public class StaplesDrawerAdapter extends RecyclerView.Adapter<StaplesDrawerAdapter.MyViewHolder> {
    List<StaplesDrawerItem> data = Collections.emptyList();
    private LayoutInflater inflater;
    private Context context;

    public StaplesDrawerAdapter(Context context, List<StaplesDrawerItem> data) {
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
        View view = inflater.inflate(R.layout.staples_drawer_row, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        StaplesDrawerItem current = data.get(position);
        holder.title.setText(current.getTitle());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.staples_name);
        }
    }
}