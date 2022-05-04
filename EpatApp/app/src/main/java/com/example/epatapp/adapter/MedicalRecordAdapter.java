package com.example.epatapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.epatapp.MedicalRecordDetailActivity;
import com.example.epatapp.R;
import com.example.epatapp.models.MedicalRecord;

import java.text.SimpleDateFormat;
import java.util.List;

public class MedicalRecordAdapter extends RecyclerView.Adapter<MedicalRecordAdapter.MedicalRecordViewHolder>{
    private Context context;
    private List<MedicalRecord> list;

    public MedicalRecordAdapter(Context context, List<MedicalRecord> list) {
        this.context = context;
        this.list = list;
    }
    public void setList(List<MedicalRecord> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MedicalRecordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_medical_record, parent, false);
        return new MedicalRecordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MedicalRecordViewHolder holder, int position) {
        MedicalRecord medicalRecord = list.get(position);
        holder.dateIn.setText(new SimpleDateFormat("dd/MM/yyyy").format(medicalRecord.getHospitalized_day()));
        holder.diseases.setText(medicalRecord.getDiseases());
        holder.diagnose.setText(medicalRecord.getDiagnose());
        holder.symptom.setText(medicalRecord.getSymptom());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MedicalRecordDetailActivity.class);
                intent.putExtra("medical_record", medicalRecord);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MedicalRecordViewHolder extends RecyclerView.ViewHolder{
        private TextView dateIn, diagnose, diseases, symptom;
        public MedicalRecordViewHolder(@NonNull View itemView) {
            super(itemView);
            dateIn = itemView.findViewById(R.id.date_in);
            diagnose = itemView.findViewById(R.id.diagnose);
            diseases = itemView.findViewById(R.id.diseases);
            symptom = itemView.findViewById(R.id.symptom);
        }
    }
}
