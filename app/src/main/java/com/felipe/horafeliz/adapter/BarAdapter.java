package com.felipe.horafeliz.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.felipe.horafeliz.R;
import com.felipe.horafeliz.model.Bar;
import com.felipe.horafeliz.model.BarDao;

public class BarAdapter extends RecyclerView.Adapter{
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ConstraintLayout elementoBarXML = (ConstraintLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_local,parent,false);

        BarViewHolder gaveta =  new BarViewHolder(elementoBarXML);

        return gaveta;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Bar bar= BarDao.obterInstancia().obterBar().get(position);
        BarViewHolder gaveta = (BarViewHolder) holder;
        gaveta.atualizaGavetaComOBarQueChegou(bar);
    }

    @Override
    public int getItemCount() {
        return BarDao.obterInstancia().obterBar().size();
    }
}
