package com.mobile.app.moonplay;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class partAdapter extends RecyclerView.Adapter<partAdapter.MiViewHolder> {

    private Context context;
    private List<parts> partsList;


    public partAdapter(Context context, List<parts> partsList) {
        this.context = context;
        this.partsList = partsList;
    }

    @NonNull
    @Override
    public MiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.parts,parent,false);
        return new MiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MiViewHolder holder, int position) {
        holder.textView.setText(partsList.get(position).getPart());
        holder.button.setText(partsList.get(position).getMb());

    }

    @Override
    public int getItemCount() {
        return partsList.size();
    }

    public class MiViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        Button button;

        public MiViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.parts);
            button=itemView.findViewById(R.id.mb);
        }
    }
}
