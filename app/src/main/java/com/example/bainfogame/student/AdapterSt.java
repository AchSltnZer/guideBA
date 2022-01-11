package com.example.bainfogame.student;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bainfogame.R;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterSt extends RecyclerView.Adapter<AdapterSt.UserViewHolder>{
    private Context mCtx;
    private ArrayList<Student> datalist;
    private OnNoteListener monNoteListener;
    public AdapterSt(Context mCtx,ArrayList<Student> datalist, OnNoteListener onNoteListener){
        this.mCtx = mCtx;
        this.datalist = datalist;
        this.monNoteListener = onNoteListener;
    }
    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.st_list, parent, false);
        return new UserViewHolder(view, monNoteListener);
    }
    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        Glide.with(mCtx).load(datalist.get(position).getImg()).into(holder.foto);

        holder.txtNama.setText(datalist.get(position).getNama());
        holder.txtType.setText(datalist.get(position).getType());
        holder.txtAff.setText(datalist.get(position).getAff());
        holder.attack.setText(datalist.get(position).getAttack());
        holder.defend.setText(datalist.get(position).getDefend());
        holder.kl.setRating(datalist.get(position).getStar());
    }
    @Override
    public int getItemCount() {
        return (datalist != null) ? datalist.size() : 0;
    }
    public class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView txtNama, txtType, txtAff, attack, defend;
        private ImageView foto;
        private RatingBar kl;
        OnNoteListener onNoteListener;
        public UserViewHolder(View itemView, OnNoteListener monNoteListener) {
            super(itemView);
            txtNama = (TextView) itemView.findViewById(R.id.nm_txt);
            txtType = (TextView) itemView.findViewById(R.id.nma_st);
            txtAff = (TextView) itemView.findViewById(R.id.aff_txt);
            attack = (TextView) itemView.findViewById(R.id.att_type);
            defend = (TextView) itemView.findViewById(R.id.deff_type);
            foto = (ImageView) itemView.findViewById(R.id.img);
            kl = (RatingBar) itemView.findViewById(R.id.ratingBar1);
        }

        @Override
        public void onClick(View view) {

        }
    }
    public interface OnNoteListener{
        void  onClick(int positon);
    }
    public void updateList(ArrayList<Student> list){
        datalist = list;
        notifyDataSetChanged();
    }
}
