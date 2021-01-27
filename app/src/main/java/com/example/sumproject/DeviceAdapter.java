package com.example.sumproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DeviceAdapter extends RecyclerView.Adapter<DeviceAdapter.ViewHolder> {
    private List<Devices> data;
    private LayoutInflater inflater;
    public DeviceAdapter(
            Context context,
            List<Devices> data
    ) {
        this.inflater = LayoutInflater.from(context);
        this.data = data;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = this.inflater.inflate(R.layout.item_list, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Devices item = data.get(position);
        if (item == null) {
            return;
        } //if
        holder.deviceName.setText(item.getName());
        holder.deviceType.setText(item.getType());
        holder.deviceStatus.setText(item.getStatus());
        holder.deviceTemp.setText(item.getTemp());
    }
    @Override
    public int getItemCount() {
        return data.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView deviceName;
        TextView deviceType;
        TextView deviceStatus;
        TextView deviceTemp;
        ViewHolder(View itemView) {
            super(itemView);
            this.deviceName = itemView.findViewById(R.id.deviceName);
            this.deviceType =
                    itemView.findViewById(R.id.deviceType);
            this.deviceStatus =
                    itemView.findViewById(R.id.deviceStatus);
            this.deviceTemp =
                    itemView.findViewById(R.id.deviceTemp);
        }
    }
    public Devices getItem(int position) {
        return data.get(position);
    }

}
