package com.felipe.horafeliz.adapter;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.felipe.horafeliz.R;
import com.felipe.horafeliz.activities.ListaActivity;
import com.felipe.horafeliz.model.Bar;
import java.util.List;

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
    private List<String> diasSemana;


    public BarViewHolder(@NonNull View itemView) {

        super(itemView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //cast do contexto para activity atual e chamada do método
                ((ListaActivity) v.getContext()).detalharBar(v, idBar);
            }
        });

        cnpj = itemView.findViewById(R.id.txt_cnpj);
        nome_local = itemView.findViewById(R.id.txt_nomelocal);
        desconto = itemView.findViewById(R.id.txt_desconto);
        img = itemView.findViewById(R.id.foto_local);
        clPai = (ConstraintLayout) itemView;
        seg = itemView.findViewById(R.id.txt_seg); ;
        ter = itemView.findViewById(R.id.txt_ter);;
        qua = itemView.findViewById(R.id.txt_quar);;
        qui = itemView.findViewById(R.id.txt_qui);;
        sex = itemView.findViewById(R.id.txt_sex);;
        sab = itemView.findViewById(R.id.txt_sab);;
        dom = itemView.findViewById(R.id.txt_dom);;
    }


    public void atualizaGavetaComOBarQueChegou(Bar bar) {
        idBar = bar.getId();

        seg.setTextColor(Color.parseColor("#d21717"));
        ter.setTextColor(Color.parseColor("#d21717"));
        qua.setTextColor(Color.parseColor("#d21717"));
        qui.setTextColor(Color.parseColor("#d21717"));
        sex.setTextColor(Color.parseColor("#d21717"));
        sab.setTextColor(Color.parseColor("#d21717"));
        dom.setTextColor(Color.parseColor("#d21717"));
        //Converte os valores para String
        //String valorAbastecido = String.valueOf(relatorio.getLitrosAbastecidos());
        //String valorKm = String.valueOf(relatorio.getKmAtual());

        //Muda os editText para os dados colocados no input
        nome_local.setText(bar.getNome());

        //cnpj.setText(bar.getCpnj());
        if (bar.isSegunda()){
            seg.setTextColor(Color.parseColor("#1bdc18"));
        }if (bar.isTerca()){
            ter.setTextColor(Color.parseColor("#1bdc18"));
        }if (bar.isQuarta()){
            qua.setTextColor(Color.parseColor("#1bdc18"));
        }if (bar.isQuinta()){
            qui.setTextColor(Color.parseColor("#1bdc18"));
        }if (bar.isSexta()){
            sex.setTextColor(Color.parseColor("#1bdc18"));
        }if (bar.isSabado()){
            sab.setTextColor(Color.parseColor("#1bdc18"));
        }if (bar.isDomingo()){
            dom.setTextColor(Color.parseColor("#1bdc18"));
        }


        //Muda o ícone de acordo com o texto do spinner
        definirImagem();

    }

    /**
     * Muda o ícone do imageView de acordo com o texto recebido
     */
    private void definirImagem(){

        img.setImageResource(R.mipmap.ic_icone_happy);
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
        }*/



    }
}
