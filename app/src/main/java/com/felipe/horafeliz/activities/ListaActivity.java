package com.felipe.horafeliz.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.BadParcelableException;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.felipe.horafeliz.R;
import com.felipe.horafeliz.adapter.BarAdapter;
import com.felipe.horafeliz.model.BarDao;


public class ListaActivity extends AppCompatActivity {

    private BarAdapter adaptador;
    private RecyclerView rvRelatorios;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        rvRelatorios = findViewById(R.id.rv_locais);

        adaptador = new BarAdapter();
        rvRelatorios.setLayoutManager(new LinearLayoutManager(this));
        rvRelatorios.setAdapter(adaptador);
    }


    public void editarRelatorio(View v, String idRelatorio){
        Intent intent = new Intent(this, CadastroActivity.class);
        intent.putExtra("idRelatorio", idRelatorio);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1){
            if(resultCode == 200){
                int posicao = data.getIntExtra("posicaoDoObjetoEditado", -1);
                adaptador.notifyItemChanged(posicao);
                rvRelatorios.smoothScrollToPosition(posicao);
            }else if(resultCode == 201){
                Toast.makeText(this,"Registro inserido!", Toast.LENGTH_LONG).show();
                int posicao = BarDao.obterInstancia().listarBares().size()-1;
                adaptador.notifyItemInserted(posicao);
                rvRelatorios.smoothScrollToPosition(posicao);
            }else if(resultCode == 202){
                Toast.makeText(this, "Registro excluído!", Toast.LENGTH_LONG).show();
                int posicao = data.getIntExtra("posicaoDoObjetoExcluido", -1);
                adaptador.notifyItemRemoved(posicao);

            }
        }
    }
    public void adicionarAbastecimento(View v){
        Intent intent = new Intent(this, CadastroActivity.class);
        startActivityForResult(intent, 1);
    }
}

