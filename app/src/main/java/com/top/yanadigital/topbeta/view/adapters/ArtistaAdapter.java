package com.top.yanadigital.topbeta.view.adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.top.yanadigital.topbeta.R;
import com.top.yanadigital.topbeta.model.vo.Artista;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArtistaAdapter extends RecyclerView.Adapter<ArtistaAdapter.ViewHolder> {

    private List<Artista> artistaList;
    private Context context;
    private OnItemClickListener listener;

    public ArtistaAdapter(List<Artista> artistaList, OnItemClickListener listener) {
        this.artistaList = artistaList;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Metodo para inflar la vista
        ViewHolder viewHolder = null;
        try {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_artist, parent, false);
            viewHolder = new ViewHolder(view);
            this.context=parent.getContext();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //Vincular vista

        final Artista artista;
        try {
            artista = artistaList.get(position);
            holder.setListener(artista,listener);
            holder.tvNombre.setText(artista.getNombreCompleto());
            holder.tvOrden.setText(String.valueOf(artista.getOrden()));

            //validar que la imagen no esta vacia

            if(artista.getFotoURL() != null){
                RequestOptions requestOptions= new RequestOptions();
                requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);//Almacenada en Cache
                requestOptions.centerCrop();//Img centrada y recortada
                //Si tarda en cargar muestra predeterminada esta imagen
                requestOptions.placeholder(R.drawable.ic_sentiment_satisfied);
                Glide.with(context)
                        .load(artista.getFotoURL())
                        .apply(requestOptions)
                        .into(holder.imgFoto);
            }else{
                holder.imgFoto.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_account_box_black));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return artistaList.size();
    }

    public void add(Artista artista){
        try {
            //si el artista no existe en la lista agregarlo
            if (!artistaList.contains(artista)) {
                artistaList.add(artista);
                notifyDataSetChanged();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imgFoto)
        AppCompatImageView imgFoto;
        @BindView(R.id.tvNombre)
        AppCompatTextView tvNombre;
        @BindView(R.id.tvOrden)
        AppCompatTextView tvOrden;
        @BindView(R.id.containerMain)
        RelativeLayout containerMain;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        void setListener(final Artista artista,final  OnItemClickListener itemClickListener){
            containerMain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClickListener.onItemClcik(artista);
                }
            });

            containerMain.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    itemClickListener.onLongItemClick(artista);
                    return true;
                }
            });
        }
    }
}
