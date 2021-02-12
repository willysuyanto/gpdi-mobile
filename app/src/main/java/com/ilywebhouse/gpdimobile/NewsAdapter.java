package com.ilywebhouse.gpdimobile;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.ilywebhouse.gpdimobile.ui.main.Berita;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.viewHolder> {
    private ArrayList<Berita> mItemList;
    private OnTitleListener mOnTitleListener;

    public NewsAdapter(ArrayList<Berita> itemList, OnTitleListener onTitleListener) {
        mItemList = itemList;
        mOnTitleListener = onTitleListener;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        viewHolder vHolder = new viewHolder(view, mOnTitleListener);
        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        String judulBerita = mItemList.get(position).getJudulberita();
        String tanggalBerita = mItemList.get(position).getTanggal();

        holder.judul.setText(judulBerita);
        holder.tanggal.setText(tanggalBerita);
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    public interface OnTitleListener {
        void OnTitleClick(int position);
    }

    public static class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView judul;
        public TextView tanggal;
        public CardView cardView;
        OnTitleListener onTitleListener;


        public viewHolder(@NonNull View itemView, OnTitleListener onTitleListener) {
            super(itemView);
            judul = itemView.findViewById(R.id.judulBerita);
            tanggal = itemView.findViewById(R.id.tanggalBerita);
            cardView = itemView.findViewById(R.id.itemNews);
            this.onTitleListener = onTitleListener;
            cardView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onTitleListener.OnTitleClick(getAdapterPosition());
        }
    }
}
