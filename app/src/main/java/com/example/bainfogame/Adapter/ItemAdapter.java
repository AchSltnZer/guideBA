package com.example.bainfogame.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.bainfogame.R;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.UserViewHolder> {

    private ArrayList<Items> dataList;
    private  OnNoteListener monNoteListener;

    public ItemAdapter(ArrayList<Items> dataList, OnNoteListener onNoteListener) {
        this.dataList = dataList;
        this.monNoteListener = onNoteListener;
    }
    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_list, parent, false);
        return new UserViewHolder(view, monNoteListener);
    }
    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        holder.foto.setImageResource(dataList.get(position).getImg());
        holder.txtNama.setText(dataList.get(position).getNama());
    }
    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }
    public class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView txtNama;
        private ImageView foto;
        OnNoteListener onNoteListener;
        public UserViewHolder(View itemView, OnNoteListener onNoteListener) {
            super(itemView);

            foto = (ImageView) itemView.findViewById(R.id.img);
            txtNama = (TextView) itemView.findViewById(R.id.info_text);
            this.onNoteListener = onNoteListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onNoteListener.onClick(getAdapterPosition());
        }
    }
    public interface OnNoteListener{
        void  onClick(int positon);
    }
}