package com.felipe.horafeliz.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.felipe.horafeliz.R;
import io.realm.Realm;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Realm.init(this);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.btn_entrar);

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
       // TextView autonomia = findViewById(R.id.txtResultadoAutonomia);
        //autonomia.setText(getAutonomia());
    }


    /**private String getAutonomia() {
        ArrayList<Bar> relatorios = BarDao.obterInstancia().listarBares();


        if (relatorios.size() <= 1) return "0.0";

        //Double quilometragem = relatorios.get(0).getKmAtual() - relatorios.get(relatorios.size() - 1).getKmAtual();
        //Double litros = 0.0;


        for (int i = 0; i < relatorios.size() - 1; i++) {
        //    litros =+ relatorios.get(i).getLitrosAbastecidos();
        }

        //String strDouble = String.format("%.2f",quilometragem/litros);

        //return strDouble;
    }*/


}
