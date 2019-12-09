package com.felipe.horafeliz.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.felipe.horafeliz.R;
import com.felipe.horafeliz.model.Bar;
import com.felipe.horafeliz.model.BarDao;

public class DetalheActivity extends AppCompatActivity {

    private String idBar;
    private TextView nome;
    private Bar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe);

        idBar = getIntent().getStringExtra("idBar");
        bar = BarDao.obterInstancia().obterBarId(idBar);


        //nome.setText(bar.getNome());



    }
}
