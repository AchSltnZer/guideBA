package com.example.bainfogame.komen;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Response;
import com.example.bainfogame.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class kmnAdapter extends RecyclerView.Adapter<kmnAdapter.kmnViewHolder>{
    private ArrayList<kmn> kmnlist;
    private  OnNotListener monNotListener;

    public kmnAdapter(ArrayList<kmn> kmnlist, OnNotListener onNotListener){
        this.kmnlist = kmnlist;
        this.monNotListener = onNotListener;
    }
    @NonNull
    @Override
    public kmnViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
       View view = layoutInflater.inflate(R.layout.kmn_list, parent,false);
       return new kmnViewHolder(view, monNotListener);
    }

@Override
public void onBindViewHolder(@NonNull kmnViewHolder holder, int position) {
        holder.txtKmn.setText(kmnlist.get(position).getKomen());
        holder.txtNama.setText(kmnlist.get(position).getNama());

    }

    @Override
    public int getItemCount() {
        return (kmnlist != null) ? kmnlist.size() : 0;
    }


    public class kmnViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView txtNama, txtKmn;
        OnNotListener onNotListener;
        public kmnViewHolder(@NonNull View itemView, OnNotListener onNotListener) {
            super(itemView);
            txtNama = (TextView) itemView.findViewById(R.id.txt_usnm);
            txtKmn = (TextView) itemView.findViewById(R.id.kmn_feed);
            this.onNotListener = onNotListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onNotListener.onClick(getAdapterPosition());
        }
    }
    public interface OnNotListener{
        void  onClick(int positon);
    }

}
