package com.felipe.horafeliz.adapter;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.felipe.horafeliz.model.Bar;

public class BarViewHolder extends RecyclerView.ViewHolder {
    private ConstraintLayout clPai;
    private String idBar;
    private ImageView img;

    public BarViewHolder(@NonNull View itemView) {
        super(itemView);
    }


    public void atualizaGavetaComOBarQueChegou(Bar bar) {
    }
}
