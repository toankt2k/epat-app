package com.example.epatapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.epatapp.R;
import com.example.epatapp.models.Treament;

import java.text.SimpleDateFormat;
import java.util.List;

public class TreamentAdapter extends RecyclerView.Adapter<TreamentAdapter.TreamentViewHolder>{
    private Context context;
    private List<Treament> list;

    public TreamentAdapter(Context context, List<Treament> list) {
        this.context = context;
        this.list = list;
    }
    public void setList(List<Treament> list){
        this.list = list;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public TreamentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_treament, parent, false);
        return new TreamentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TreamentViewHolder holder, int position) {
        Treament treament = list.get(position);
        holder.date.setText(new SimpleDateFormat("dd/MM/yyyy").format(treament.getDate()));
        holder.time.setText(new SimpleDateFormat("HH:mm:ss").format(treament.getDate()));
        holder.progress.setText(treament.getProgress());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class TreamentViewHolder extends RecyclerView.ViewHolder{
        private TextView date, time, progress;
        public TreamentViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.date_treament);
            time = itemView.findViewById(R.id.time_treament);
            progress = itemView.findViewById(R.id.progress);
        }
    }
}
