package com.bkane56.grocerylist.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bkane56.grocerylist.R;
import com.bkane56.grocerylist.StaplesList;
import com.bkane56.grocerylist.items.StaplesListItem;

import java.util.Collections;
import java.util.List;


public class StaplesListAdapter extends RecyclerView.Adapter<StaplesListAdapter.MyViewHolder> {

    List<StaplesListItem> data = Collections.emptyList();
    private LayoutInflater inflater;
    private Context context;

    public StaplesListAdapter(Context context, List<StaplesListItem> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    public void delete(int position) {
        data.remove(position);
        notifyItemRemoved(position);
    }

    public void swap(List<StaplesListItem> datas){
        data.clear();
        data.addAll(datas);
        notifyDataSetChanged();
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.staples_list_recycler_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        StaplesListItem current = data.get(position);
        StaplesList mStaplesList = new StaplesList(context);
        if (data.size() == mStaplesList.getSize()){
            holder.staplesType.setText(current.getStapleType());
            holder.staplesType.setVisibility(View.VISIBLE);
        }

        holder.item.setText(current.getStaplesItem());
//        sets the view checkbox to the items current state(false)
        holder.isSelected.setChecked(current.isChecked());
        holder.isSelected.setTag(current);
//        set on click listener for view checkbox
        holder.isSelected.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CheckBox cb = (CheckBox) v;
                StaplesListItem staple = (StaplesListItem) cb.getTag();
//                set the items checked status to the view checkbox status
                staple.setIsChecked(cb.isChecked());
                data.get(position).setIsChecked(cb.isChecked());

            }
        });
        }

    @Override
    public int getItemCount() {
        return data.size();
    }
    public List<StaplesListItem> getData(){
        return data;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView item;
        CheckBox isSelected;
        TextView staplesType;

        public MyViewHolder(View itemView) {
            super(itemView);
            item = (TextView) itemView.findViewById(R.id.staples_item);
            isSelected = (CheckBox) itemView.findViewById(R.id.cb_isChecked);
            staplesType = (TextView) itemView.findViewById(R.id.staples_type);
        }
    }
}


