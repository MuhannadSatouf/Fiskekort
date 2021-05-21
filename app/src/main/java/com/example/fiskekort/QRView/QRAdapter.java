package com.example.fiskekort.QRView;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fiskekort.R;

import java.util.ArrayList;

public class QRAdapter extends RecyclerView.Adapter<QRAdapter.ViewHolder> {

    Context context;
    ArrayList<Bitmap> images;
    ArrayList<String> kommuns;
    ArrayList<String> description;


    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView kommun;
        TextView rowDescription;
        ImageView rowImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            kommun = itemView.findViewById(R.id.kommun);
            rowDescription = itemView.findViewById(R.id.description);
            rowImage = (ImageView) itemView.findViewById(R.id.qe_ImageView);
        }
    }

    public QRAdapter(Context context, ArrayList<String> kommuns, ArrayList<String> description, ArrayList<Bitmap> images) {
        this.kommuns = kommuns;
        this.description = description;
        this.context = context;
        this.images = images;
    }

    @NonNull
    @Override
    public QRAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.qe_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull QRAdapter.ViewHolder holder, int position) {
        holder.kommun.setText(kommuns.get(position));
        holder.rowDescription.setText(description.get(position));

        System.out.println(images.size());
        holder.rowImage.setImageBitmap(images.get(position));

    }

    @Override
    public int getItemCount() {
        return kommuns.size();
    }
}
