package com.ilywebhouse.gpdimobile;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SubMenuAdapter extends RecyclerView.Adapter<SubMenuAdapter.viewHolder> {
    private ArrayList<SubMenuItem> mItemList;
    private OnTitleListener mOnTitleListener;

    public SubMenuAdapter(ArrayList<SubMenuItem> itemList, OnTitleListener onTitleListener) {
        mItemList = itemList;
        mOnTitleListener = onTitleListener;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.submenu_items, parent, false);
        viewHolder vHolder = new viewHolder(view, mOnTitleListener);
        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        String title = mItemList.get(position).getTitle();
        String subTitle = mItemList.get(position).getSubTitle();
        holder.title.setText(title);
        holder.subTitle.setText(subTitle);
        holder.icon_menu.setImageResource(mItemList.get(position).getIcon());
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    public interface OnTitleListener {
        void OnTitleClick(int position);
    }

    public static class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView title;
        public TextView subTitle;
        public ImageView icon_menu;
        public LinearLayout linearLayout;
        OnTitleListener onTitleListener;


        public viewHolder(@NonNull View itemView, OnTitleListener onTitleListener) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_title);
            subTitle = itemView.findViewById(R.id.tv_subtitle);
            icon_menu = itemView.findViewById(R.id.icon_submenu);
            linearLayout = itemView.findViewById(R.id.layout_submenu);
            this.onTitleListener = onTitleListener;
            linearLayout.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onTitleListener.OnTitleClick(getAdapterPosition());
        }
    }
}
