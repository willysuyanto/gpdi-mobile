package com.ilywebhouse.gpdimobile;


import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.viewHolder> {
    private ArrayList<MenuItem> mItemList;
    private OnTitleListener mOnTitleListener;

    public MenuAdapter(ArrayList<MenuItem> itemList, OnTitleListener onTitleListener) {
        mItemList = itemList;
        mOnTitleListener = onTitleListener;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_items, parent, false);
        viewHolder vHolder = new viewHolder(view, mOnTitleListener);
        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        String menuLabel = mItemList.get(position).getMenuLabel();
        holder.text_menu.setText(menuLabel);
        holder.icon_menu.setImageResource(mItemList.get(position).getMenuIcon());
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    public interface OnTitleListener {
        void OnTitleClick(int position);
    }

    public static class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView text_menu;
        public ImageView icon_menu;
        public CardView cardView;
        OnTitleListener onTitleListener;


        public viewHolder(@NonNull View itemView, OnTitleListener onTitleListener) {
            super(itemView);
            text_menu = itemView.findViewById(R.id.tv_menu);
            icon_menu = itemView.findViewById(R.id.icon_menu);
            cardView = itemView.findViewById(R.id.menuItem);
            this.onTitleListener = onTitleListener;
            cardView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onTitleListener.OnTitleClick(getAdapterPosition());
        }
    }
}
