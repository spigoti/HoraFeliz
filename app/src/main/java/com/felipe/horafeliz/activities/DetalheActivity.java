package com.felipe.horafeliz.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.felipe.horafeliz.R;
import com.felipe.horafeliz.model.Bar;
import com.felipe.horafeliz.model.BarDao;

public class DetalheActivity extends AppCompatActivity {

    private String idBar;
    private TextView nome;
    private TextView cervejaria;
    private TextView prato;
    private Bar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe);

        nome = findViewById(R.id.txt_detalheNome);
        cervejaria = findViewById(R.id.txt_detalheCervejaria);
        prato = findViewById(R.id.txt_detalhePratoChefe);


        idBar = getIntent().getStringExtra("idBar");
        bar = BarDao.obterInstancia().obterBarId(idBar);


        nome.setText(bar.getNome());
        Button buttonMapa = findViewById(R.id.btn_mapa);
        Button buttonEditar = findViewById(R.id.btn_editar);



        //Evento para ir para a tela do mapa
        buttonMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetalheActivity.this, MapaActivity.class);
                intent.putExtra("idBar", idBar);
                startActivityForResult(intent, 1);
            }
        });

        //Evento para ir para a tela da edição
        buttonEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetalheActivity.this, CadastroActivity.class);
                intent.putExtra("idBar", idBar);
                startActivityForResult(intent, 1);
            }
        });


    }
}
