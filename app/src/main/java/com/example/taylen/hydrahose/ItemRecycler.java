package com.example.taylen.hydrahose;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ItemRecycler extends RecyclerView.Adapter<ItemRecycler.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvLine1, tvLine2;
        public Item item;
        public View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            tvLine1 = (TextView) itemView.findViewById(R.id.line1);
            tvLine2 = (TextView) itemView.findViewById(R.id.line2);
        }
    }

    //Attributes
    private final List<Item> liItems;

    public ItemRecycler(List<Item> liItems) {
        this.liItems = liItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false);

        //return new ViewHolder(view);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemRecycler.ViewHolder holder, int position) {
        final Item item = liItems.get(position);
        if (item != null) {
            holder.item = item;
            holder.tvLine1.setText(item.getName());
            holder.tvLine2.setText("Part Number: " + item.getPart_number());

            holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //TODO: userClicked

                }
            });

            /*
            holder.view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                }
            });
            */
        }
    }

    @Override
    public int getItemCount() {
        return liItems.size();
    }

    public void addItems(List<Item> users) {
        this.liItems.clear();
        this.liItems.addAll(users);
        notifyDataSetChanged();
    }

    public void clear() {
        this.liItems.clear();
        notifyDataSetChanged();
    }


}
