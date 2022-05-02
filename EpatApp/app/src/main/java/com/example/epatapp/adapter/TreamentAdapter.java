package com.example.epatapp.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.epatapp.R;
import com.google.gson.JsonObject;

public class TreamentAdapter extends RecyclerView.Adapter<TreamentAdapter.TreamentViewHolder>{

    @NonNull
    @Override
    public TreamentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull TreamentViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class TreamentViewHolder extends RecyclerView.ViewHolder{
        private TextView date, time, temp, heal_atm, heart, spo2;
        public TreamentViewHolder(@NonNull View view) {
            super(view);
            date = view.findViewById(R.id.date);
            time = view.findViewById(R.id.time);
            temp = view.findViewById(R.id.temp);
            heal_atm = view.findViewById(R.id.heal_atm);
            heart = view.findViewById(R.id.heart);
            spo2 = view.findViewById(R.id.spo2);
            JsonObject jsonObject = new JsonObject();
        }
    }
}
