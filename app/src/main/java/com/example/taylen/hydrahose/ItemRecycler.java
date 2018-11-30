package com.example.taylen.hydrahose;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false);

        //return new ViewHolder(view);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemRecycler.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


}
