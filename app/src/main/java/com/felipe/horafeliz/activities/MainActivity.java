package com.felipe.horafeliz.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.felipe.relatorioabastecimento.Model.RelatorioDAO;
import com.felipe.relatorioabastecimento.Model.RelatorioModel;

import java.text.DecimalFormat;
import java.util.ArrayList;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Realm.init(this);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.btnHistorico);

        //Evento para ir para a tela da Lista
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListaActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        TextView autonomia = findViewById(R.id.txtResultadoAutonomia);
        autonomia.setText(getAutonomia());
    }


    private String getAutonomia() {
        ArrayList<RelatorioModel> relatorios = RelatorioDAO.obterInstancia().obterLista();


        if (relatorios.size() <= 1) return "0.0";

        Double quilometragem = relatorios.get(0).getKmAtual() - relatorios.get(relatorios.size() - 1).getKmAtual();
        Double litros = 0.0;


        for (int i = 0; i < relatorios.size() - 1; i++) {
            litros =+ relatorios.get(i).getLitrosAbastecidos();
        }

        String strDouble = String.format("%.2f",quilometragem/litros);

        return strDouble;
    }


}
