package com.pet502.mascotas502gt;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import com.bumptech.glide.Glide;

import java.util.List;

public class Adapter2 extends RecyclerView.Adapter<Adapter2.PlayerViewnHolder> {

    private Context mCtx;
    private List<M_encontradas>mncontradasList;

    public Adapter2(Context mCtx,List<M_encontradas>mncontradasList){
        this.mCtx=mCtx;
        this.mncontradasList=mncontradasList;
    }

    @NonNull
    @Override
    public PlayerViewnHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(mCtx);
        View view=inflater.inflate(R.layout.lista2,null);

        return new PlayerViewnHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerViewnHolder holder, int position) {

        M_encontradas mEncontradas=mncontradasList.get(position);

        Glide.with(mCtx).load(mEncontradas.getImg()).into(holder.img222);

        holder.tv11.setText(mEncontradas.getGenero());
        holder.tv22.setText(mEncontradas.getDireccion());
        holder.tv33.setText(mEncontradas.getContacto());

    }

    @Override
    public int getItemCount() {
        return mncontradasList.size();
    }


    static class PlayerViewnHolder extends  RecyclerView.ViewHolder{
        TextView tv11,tv22,tv33;
        ImageView img222;
        public PlayerViewnHolder(@NonNull View itemView) {
            super(itemView);

            tv11=itemView.findViewById(R.id.tv11);
            tv22=itemView.findViewById(R.id.tv22);
            tv33=itemView.findViewById(R.id.tv33);
            img222=itemView.findViewById(R.id.img222);
        }
    }

}
