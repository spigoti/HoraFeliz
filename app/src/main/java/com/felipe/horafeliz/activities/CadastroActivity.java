package com.felipe.horafeliz.activities;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.felipe.relatorioabastecimento.Model.RelatorioDAO;
import com.felipe.relatorioabastecimento.Model.RelatorioModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CadastroActivity extends AppCompatActivity {

    private RelatorioModel relatorio;
    private String idRelatorio;
    private EditText editKm;
    private EditText editLitros;
    private EditText editData;
    private Spinner postos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        editKm = findViewById(R.id.inputKm);
        editLitros = findViewById(R.id.inputLitros);
        editData = findViewById(R.id.datePicker);
        postos = findViewById(R.id.spinnerPostos);
        editData.setKeyListener(null);

        String[] opcoesPostos = getResources().getStringArray(R.array.opcoes_postos);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opcoesPostos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        postos.setAdapter(adapter);

        //Recebe o id do Relatório
        idRelatorio = getIntent().getStringExtra("idRelatorio");

        //Se não existir um relatório, abre a tela para cadastrar um novo e deixa o botão excluir invisível
        if(idRelatorio == null){
            relatorio = new RelatorioModel();
            Button btnExcluir = findViewById(R.id.btnExcluir);
            btnExcluir.setVisibility(View.INVISIBLE);
        }else{
            relatorio = RelatorioDAO.obterInstancia().obterRelatorioPeloId(idRelatorio);
            editKm.setText(String.valueOf(relatorio.getKmAtual()));
            editLitros.setText(String.valueOf(relatorio.getLitrosAbastecidos()));

            for(int i = 0; i < postos.getAdapter().getCount(); i++){
                if (postos.getAdapter().getItem(i).toString().equalsIgnoreCase( relatorio.getPosto())){
                    postos.setSelection(i+1);
                    break;
                }
            }

            @SuppressLint("SimpleDateFormat") DateFormat formatadorDeData = new SimpleDateFormat("dd/MM/yyyy");
            String dataSelecionadaFormatada = formatadorDeData.format( relatorio.getData().getTime());
            editData.setText( dataSelecionadaFormatada );
        }

    }

    public void salvar(View v){

        relatorio.setKmAtual(Double.parseDouble(editKm.getText().toString()));
        relatorio.setLitrosAbastecidos(Double.parseDouble(editLitros.getText().toString()));
        relatorio.setPosto(postos.getSelectedItem().toString());

        if(idRelatorio == null) {
            RelatorioDAO.obterInstancia().adicionarNaLista(relatorio);
            setResult(201);
        }else{
            int posicaoDoObjeto = RelatorioDAO.obterInstancia().atualizarNaLista(relatorio);
            Intent intentCadastroActivity = new Intent();
            intentCadastroActivity.putExtra("posicaoDoObjetoEditado", posicaoDoObjeto);
            setResult(200, intentCadastroActivity);
        }
        finish();

    }


    public void excluir(View v){
        new AlertDialog.Builder(this)
                .setTitle("Excluir um relatório")
                .setMessage("Deseja excluir esse item?")
                .setPositiveButton("Excluir", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int posicaoDoObjeto = RelatorioDAO.obterInstancia().excluirDaLista(relatorio);
                        Intent intentCadastroActivity = new Intent();
                        intentCadastroActivity.putExtra("posicaoDoObjetoExcluido", posicaoDoObjeto);
                        setResult(202, intentCadastroActivity);
                        finish();
                    }
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }

}