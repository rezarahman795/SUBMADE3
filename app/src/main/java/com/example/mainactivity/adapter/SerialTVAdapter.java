package com.example.mainactivity.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mainactivity.R;
import com.example.mainactivity.model.SerialTV;

import java.util.ArrayList;

public class SerialTVAdapter extends RecyclerView.Adapter<SerialTVAdapter.SerialViewHolder> {

    ArrayList<SerialTV> listSerialTV ;
    private OnItemClickCallback onItemClickCallback;

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public SerialTVAdapter(ArrayList<SerialTV> listSerialTV) {
        this.listSerialTV = listSerialTV;
    }

    @NonNull
    @Override
    public SerialTVAdapter.SerialViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_serial_tv,parent,false);
        return new SerialViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final SerialViewHolder holder, int position) {
        SerialTV mySerialTV = listSerialTV.get(position);

        holder.txtNameSerial.setText(mySerialTV.getNamaSerial());
        holder.txtTglSerial.setText(mySerialTV.getTglSerial());
        holder.txtCrewSerial.setText(mySerialTV.getCrewSerial());
        holder.txtCrewPositionSerial.setText(mySerialTV.getCrewDetailSerial());
        holder.txtDescSerial.setText(mySerialTV.getDescSerial());
        holder.pictSerial.setImageResource(mySerialTV.getPicture());

        Glide.with(holder.itemView.getContext())
                .load(mySerialTV.getPicture())
                .apply(new RequestOptions().override(350,550))
                .into(holder.pictSerial);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(listSerialTV.get(holder.getAdapterPosition()));

            }
        });

    }

    @Override
    public int getItemCount() {
        return listSerialTV.size();
    }

    public class SerialViewHolder extends RecyclerView.ViewHolder {
        TextView txtNameSerial,txtTglSerial,txtDescSerial,txtCrewSerial,txtCrewPositionSerial;
        ImageView pictSerial;

        public SerialViewHolder(View itemView) {
            super(itemView);

            pictSerial=itemView.findViewById(R.id.item_poster_serial);
            txtNameSerial= itemView.findViewById(R.id.item_txt_name_serial);
            txtTglSerial= itemView.findViewById(R.id.item_txt_tgl_serial);
            txtDescSerial= itemView.findViewById(R.id.txt_desc_serial);
            txtCrewSerial= itemView.findViewById(R.id.item_crew_serial);
            txtCrewPositionSerial= itemView.findViewById(R.id.item_position_serial);
        }
    }
    public interface OnItemClickCallback {

        void onItemClicked(SerialTV dataSerial);
    }
}
