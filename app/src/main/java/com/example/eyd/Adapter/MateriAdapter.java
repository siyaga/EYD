package com.example.eyd.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eyd.Model.ModelMateri;
import com.example.eyd.R;

import java.util.ArrayList;

public class MateriAdapter extends RecyclerView.Adapter<MateriAdapter.ListViewHolder> {
    private ArrayList<ModelMateri> listMateri;
    private OnItemClickCallback onItemClickCallback;

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }
    public MateriAdapter(ArrayList<ModelMateri> list){
        this.listMateri = list;
    }



    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_materi, viewGroup, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder holder, int position) {
        ModelMateri modelMateri = listMateri.get(position);
        holder.TvMateri.setText(modelMateri.getMateri());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(listMateri.get(holder.getAdapterPosition()));
            }
        });

    }

    @Override
    public int getItemCount() {
        return listMateri.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        TextView TvMateri;
        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            TvMateri = itemView.findViewById(R.id.tv_materi_rv);
        }
    }
    public interface OnItemClickCallback {
        void onItemClicked(ModelMateri modelMateri);
    }
}
