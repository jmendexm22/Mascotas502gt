package com.pet502.mascotas502gt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class Adapter3 extends RecyclerView.Adapter<Adapter3.PlayerViewnHolder> {

    private Context mCtx;
    private List<Adopcion>adopcionList;

    public Adapter3(Context mCtx,List<Adopcion>adopcionList){
        this.mCtx=mCtx;
        this.adopcionList=adopcionList;
    }

    @NonNull
    @Override
    public PlayerViewnHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(mCtx);
        View view=inflater.inflate(R.layout.lista3,null);

        return new PlayerViewnHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerViewnHolder holder, int position) {
        Adopcion adopcion = adopcionList.get(position);
        Glide.with(mCtx).load(adopcion.getImg()).into(holder.img2222);

        holder.tv111.setText(adopcion.getRaza());
        holder.tv222.setText(adopcion.getEdad());
        holder.tv333.setText(adopcion.getSexo());
        holder.contacto22.setText(adopcion.getContacto());

    }

    @Override
    public int getItemCount() {
        return adopcionList.size();
    }


    public class PlayerViewnHolder extends RecyclerView.ViewHolder {
    TextView tv111,tv222,tv333,contacto22;
    ImageView img2222;

        public PlayerViewnHolder(@NonNull View itemView) {
            super(itemView);

            tv111=itemView.findViewById(R.id.tv111);
            tv222=itemView.findViewById(R.id.tv222);
            tv333=itemView.findViewById(R.id.tv333);
            contacto22=itemView.findViewById(R.id.contacto22);
            img2222=itemView.findViewById(R.id.img2222);
        }


    }


}
