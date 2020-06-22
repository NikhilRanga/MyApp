package com.example.myapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import java.util.List;

public  class MyCardAdapter2 extends RecyclerView.Adapter<MyCardAdapter2.ViewHolder> {

    private List<carditem2> carditems;
    private Context context;
    public MyCardAdapter2(List<carditem2> carditems,Context context)
    {
        this.carditems=carditems;
        this.context=context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview2,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        carditem2 item=carditems.get(position);
        holder.roleid.setText(item.getRoleid());
        holder.applicationid.setText(item.getAppid());
    }

    @Override
    public int getItemCount() {
        return carditems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView roleid;
        public TextView applicationid;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            roleid=(TextView) itemView.findViewById(R.id.roleid);
            applicationid=(TextView) itemView.findViewById(R.id.appid);
        }
    }
}


