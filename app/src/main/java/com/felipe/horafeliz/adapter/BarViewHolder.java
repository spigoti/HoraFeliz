package com.felipe.horafeliz.adapter;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.felipe.horafeliz.R;
import com.felipe.horafeliz.activities.ListaActivity;
import com.felipe.horafeliz.model.Bar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class BarViewHolder extends RecyclerView.ViewHolder {
    private ConstraintLayout clPai;
    private String idBar;
    private ImageView img;
    private TextView seg;
    private TextView ter;
    private TextView qua;
    private TextView qui;
    private TextView sex;
    private TextView sab;
    private TextView dom;
    private TextView nome_local;
    private TextView cnpj;
    private TextView horarioInicio;
    private TextView horarioFim;
    private TextView desconto;


    public BarViewHolder(@NonNull View itemView) {

        super(itemView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //cast do contexto para activity atual e chamada do método
                ((ListaActivity) v.getContext()).editarBar(v, idBar);
            }
        });

        cnpj = itemView.findViewById(R.id.txt_cnpj);
        nome_local = itemView.findViewById(R.id.txt_nomelocal);
        desconto = itemView.findViewById(R.id.txt_desconto);
        //img = itemView.findViewById(R.id.iconePosto);
        clPai = (ConstraintLayout) itemView;
    }


    public void atualizaGavetaComOBarQueChegou(Bar bar) {
        idBar = bar.getId();


        //Converte os valores para String
        //String valorAbastecido = String.valueOf(relatorio.getLitrosAbastecidos());
        //String valorKm = String.valueOf(relatorio.getKmAtual());

        //Muda os editText para os dados colocados no input
        nome_local.setText(bar.getNome());
        //cnpj.setText(bar.getCpnj());


        //Muda o ícone de acordo com o texto do spinner
        //definirImagem(relatorio.getPosto());

    }

    /**
     * Muda o ícone do imageView de acordo com o texto recebido
     * @param posto
     */
    private void definirImagem(String posto){

        /**if(posto.equals("Ipiranga")){
            img.setImageResource(R.mipmap.ic_ipiranga_foreground);
        }else if(posto.equals("Petrobras")){
            img.setImageResource(R.mipmap.ic_petrobras_foreground);
        }else if(posto.equals("Shell")){
            img.setImageResource(R.mipmap.ic_shell_foreground);
        }else if(posto.equals("Texaco")){
            img.setImageResource(R.mipmap.ic_texaco_foreground);
        }else{
            img.setImageResource(R.mipmap.ic_outros_foreground);
        }**/



    }
}
