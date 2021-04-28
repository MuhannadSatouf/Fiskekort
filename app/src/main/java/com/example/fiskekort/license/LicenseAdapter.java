package com.example.fiskekort.license;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;


import com.satouf.fiskekort.R;

import com.example.fiskekort.R;


import java.util.List;

public class LicenseAdapter extends RecyclerView.Adapter<LicenseAdapter.LicenseViewHolder> {
    private List<LicenseModel> licenseModelList;
    private Context context;
    private OnRecyclerItemClick onRecyclerItemClick;

    public LicenseAdapter(List<LicenseModel> licenseModelList, Context context){
        this.licenseModelList = licenseModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public LicenseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LicenseViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.license_items, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LicenseViewHolder holder, int position) {
        holder.licenseNameText.setText(this.licenseModelList.get(position).getName());
        holder.descText.setText(this.licenseModelList.get(position).getDesc());
        holder.licenseIdText.setText(this.licenseModelList.get(position).getId());

        switch (position){
            case 0:
                holder.view.setBackgroundColor(ContextCompat.getColor(context, R.color.licence_color1));
                break;
            case 1:
                holder.view.setBackgroundColor(ContextCompat.getColor(context, R.color.licence_color2));
                break;
            case 2:
                holder.view.setBackgroundColor(ContextCompat.getColor(context, R.color.licence_color3));
                break;
            case 3:
                holder.view.setBackgroundColor(ContextCompat.getColor(context, R.color.licence_color4));
                break;
            case 5:
                holder.view.setBackgroundColor(ContextCompat.getColor(context, R.color.licence_color5));
                break;
            default:
                holder.view.setBackgroundColor(ContextCompat.getColor(context, R.color.purple_700));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return licenseModelList.size();
    }

    public class LicenseViewHolder extends RecyclerView.ViewHolder {
        private TextView licenseNameText, descText, licenseIdText;
        private View view;

        public LicenseViewHolder(@NonNull View itemView) {
            super(itemView);
            licenseNameText = itemView.findViewById(R.id.license_head1);
            descText = itemView.findViewById(R.id.license_desc1);
            licenseIdText = itemView.findViewById(R.id.license_time1);
            view = itemView.findViewById(R.id.license_view1);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onRecyclerItemClick != null){
                        int pos = getAdapterPosition();
                        if(pos != RecyclerView.NO_POSITION){
                            onRecyclerItemClick.onClick(pos);
                        }
                    }
                }
            });
        }
    }

    public void setOnRecyclerItemClick(OnRecyclerItemClick onRecyclerItemClick){
        this.onRecyclerItemClick = onRecyclerItemClick;
    }

    interface OnRecyclerItemClick {
        void onClick(int position);
    }
}
