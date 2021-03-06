package com.example.eyd.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eyd.Model.ModelMateri;
import com.example.eyd.Model.ModelSoal;
import com.example.eyd.R;
import com.example.eyd.Soal;

import java.util.ArrayList;

public class SoalAdapter extends RecyclerView.Adapter<SoalAdapter.ListViewHolder>  {
    private ArrayList<ModelSoal> listSoal;
    private OnItemClickCallback onItemClickCallback;

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }
    public SoalAdapter(ArrayList<ModelSoal> list){
        this.listSoal = list;
    }
    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_review_soal, viewGroup, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder holder, int position) {
        ModelSoal modelSoal = listSoal.get(position);
        holder.TVsoal.setText(modelSoal.getSoal());
        holder.rbjwb1.setText(modelSoal.getJawaban1());
        holder.rbjwb2.setText(modelSoal.getJawaban2());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(listSoal.get(holder.getAdapterPosition()));
            }
        });

    }

    @Override
    public int getItemCount() {
        return listSoal.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        TextView TVsoal;
        RadioButton rbjwb1, rbjwb2;
        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            TVsoal = itemView.findViewById(R.id.tv_soal);
            rbjwb1 = itemView.findViewById(R.id.rb_jawaban_1);
            rbjwb2 = itemView.findViewById(R.id.rb_jawaban_2);
        }
    }
    public interface OnItemClickCallback {
        void onItemClicked(ModelSoal modelSoal);
    }
}


