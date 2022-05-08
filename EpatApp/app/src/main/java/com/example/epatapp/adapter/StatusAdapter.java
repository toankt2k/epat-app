package com.example.epatapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.epatapp.R;
import com.example.epatapp.models.Status;
import com.google.gson.JsonObject;

import java.text.SimpleDateFormat;
import java.util.List;

public class StatusAdapter extends RecyclerView.Adapter<StatusAdapter.StatusViewHolder>{
    private Context context;
    private List<Status> list;

    public StatusAdapter(Context context, List<Status> list) {
        this.context = context;
        this.list = list;
    }

    public void setList(List<Status> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public StatusViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_status, parent, false);
        return new StatusViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StatusViewHolder holder, int position) {
        Status status = list.get(position);
        holder.date.setText(new SimpleDateFormat("dd/MM/yyyy").format(status.getDate()));
        holder.time.setText(new SimpleDateFormat("HH:mm:ss").format(status.getDate()));
        holder.temp.setText(status.getTemp()+"°C");
        holder.heal_atm.setText(status.getHeal_atm()+"mmHg");
        holder.heart.setText(status.getHeart()+"bpm");
        holder.spo2.setText(status.getSpo2()+"%");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class StatusViewHolder extends RecyclerView.ViewHolder{
        private TextView date, time, temp, heal_atm, heart, spo2;
        public StatusViewHolder(@NonNull View view) {
            super(view);
            date = view.findViewById(R.id.date);
            time = view.findViewById(R.id.time);
            temp = view.findViewById(R.id.temp);
            heal_atm = view.findViewById(R.id.heal_atm);
            heart = view.findViewById(R.id.heart);
            spo2 = view.findViewById(R.id.spo2);
        }
    }
}
