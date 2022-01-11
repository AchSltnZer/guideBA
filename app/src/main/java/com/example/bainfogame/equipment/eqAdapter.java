package com.example.bainfogame.equipment;

import static com.example.bainfogame.R.layout.table_list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bainfogame.R;

import java.util.ArrayList;
import java.util.List;

public class eqAdapter extends RecyclerView.Adapter<eqAdapter.UserViewHolder> {
    private Context ctx;
    private ArrayList<eqmodel> eq_list;


    public eqAdapter(Context ctx, ArrayList<eqmodel> eq_list){
        this.ctx = ctx;
        this.eq_list = eq_list;
    }



    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(ctx).inflate(table_list, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        if (eq_list != null){
            eqmodel model = eq_list.get(position);
            Glide.with(ctx).load(model.getImg()).into(holder.img);
            holder.r1.setText(model.getTier());
            holder.r2.setText(model.getNama());
            holder.r3.setText(model.getMapd());
        }else {
            return;
        }
    }

    @Override
    public int getItemCount() {
        return eq_list.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        TextView r1,r2,r3;
        ImageView img;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imgeq);
            r1 = itemView.findViewById(R.id.tbl1);
            r2 = itemView.findViewById(R.id.tbl2);
            r3 = itemView.findViewById(R.id.tbl3);
        }
    }
    public void updatelist(ArrayList<eqmodel> list){
        eq_list = list;
        notifyDataSetChanged();
    }

}
