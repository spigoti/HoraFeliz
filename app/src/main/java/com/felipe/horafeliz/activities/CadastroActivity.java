package com.felipe.horafeliz.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import com.felipe.horafeliz.R;
import com.felipe.horafeliz.model.Bar;
import com.felipe.horafeliz.model.BarDao;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class CadastroActivity extends AppCompatActivity {

    private Bar bar;
    private String idBar;
    private EditText nome;
    private EditText cnpj;
    private EditText horario_inicio;
    private EditText horario_fim;
    private CheckBox seg;
    private CheckBox ter;
    private CheckBox qua;
    private CheckBox qui;
    private CheckBox sex;
    private CheckBox sab;
    private CheckBox dom;
    private Spinner descontos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        nome = findViewById(R.id.input_NomeLocal);
        cnpj = findViewById(R.id.inputCnpj);
        horario_inicio = findViewById(R.id.horario_inicio);
        horario_fim = findViewById(R.id.horario_fim);
        descontos = findViewById(R.id.spinnerDesconto);
        seg = findViewById(R.id.checkBoxSeg);
        ter = findViewById(R.id.checkBoxTer);
        qua = findViewById(R.id.checkBoxQuar);
        qui = findViewById(R.id.checkBoxQui);
        sex = findViewById(R.id.checkBoxSex);
        sab = findViewById(R.id.checkBoxSab);
        dom = findViewById(R.id.checkBoxDomingo);


        String[] opcoesDescontos = getResources().getStringArray(R.array.opcoes_desconto);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opcoesDescontos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        descontos.setAdapter(adapter);

        //Recebe o id do Bar
        idBar = getIntent().getStringExtra("idBar");

        //Se não existir um Bar, abre a tela para cadastrar um novo e deixa o botão excluir invisível
        if(idBar == null){
            bar = new Bar();
            Button btnExcluir = findViewById(R.id.btnExcluir);
            btnExcluir.setVisibility(View.INVISIBLE);
        }else{
            bar = BarDao.obterInstancia().obterBarId(idBar);
            cnpj.setText(String.valueOf(bar.getCpnj()));
            seg.setChecked(bar.isSegunda());
            ter.setChecked(bar.isTerca());
            qua.setChecked(bar.isQuarta());
            qui.setChecked(bar.isQuinta());
            sex.setChecked(bar.isSexta());
            sab.setChecked(bar.isSabado());
            dom.setChecked(bar.isDomingo());
            nome.setText(String.valueOf(bar.getNome()));


            for(int i = 0; i < descontos.getAdapter().getCount(); i++){
                if (descontos.getAdapter().getItem(i).toString().equalsIgnoreCase( bar.getDesconto())){
                    descontos.setSelection(i+1);
                    break;
                }
            }

        }

    }

    public void salvar(View v){

        bar.setNome(nome.getText().toString());
        bar.setCpnj(cnpj.getText().toString());
        bar.setDesconto(descontos.getSelectedItem().toString());

        //Segunda
        if (seg.isChecked()){
            bar.setSegunda(true);
        }else {
            bar.setSegunda(false);
        }

        //Terça
        if (ter.isChecked()){
            bar.setTerca(true);
        }else {
            bar.setTerca(false);
        }

        //Quarta
        if (qua.isChecked()){
            bar.setQuarta(true);
        }else {
            bar.setQuarta(false);
        }

        //Quinta
        if (qui.isChecked()){
            bar.setQuinta(true);
        }else {
            bar.setQuinta(false);
        }

        //Sexta
        if (sex.isChecked()){
            bar.setSexta(true);
        }else {
            bar.setSexta(false);
        }

        //Sabado
        if (sab.isChecked()){
            bar.setSabado(true);
        }else {
            bar.setSabado(false);
        }

        //Domingo
        if (dom.isChecked()){
            bar.setDomingo(true);
        }else {
            bar.setDomingo(false);
        }


        if(idBar == null) {
            BarDao.obterInstancia().adicionarBarNaLista(bar);
            setResult(201);
        }else{
            int posicaoDoObjeto = BarDao.obterInstancia().atualizaBarNaLista(bar);
            Intent intentCadastroActivity = new Intent(CadastroActivity.this, ListaActivity.class);
            intentCadastroActivity.putExtra("posicaoDoObjetoEditado", posicaoDoObjeto);
            startActivity(intentCadastroActivity);
        }
        finish();

    }


    public void excluir(View v){
        new AlertDialog.Builder(this)
                .setTitle("Excluir um local")
                .setMessage("Deseja excluir esse item?")
                .setPositiveButton("Excluir", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int posicaoDoObjeto = BarDao.obterInstancia().excluiBarDaLista(bar);
                        Intent intentCadastroActivity = new Intent();
                        intentCadastroActivity.putExtra("posicaoDoObjetoExcluido", posicaoDoObjeto);
                        setResult(202, intentCadastroActivity);
                        finish();
                    }
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }


    String caminhoDaFoto = null;

    private File criarArquivoParaSalvarFoto() throws IOException {
        String nomeFoto = UUID.randomUUID().toString();
        //getExternalStoragePublicDirectory()
        //    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
        File diretorio = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File fotografia = File.createTempFile(nomeFoto, ".jpg", diretorio);
        caminhoDaFoto = fotografia.getAbsolutePath();
        return fotografia;
    }


    public void abrirCamera(View v) {
        Intent intecaoAbrirCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        File arquivoDaFoto = null;
        try {
            arquivoDaFoto = criarArquivoParaSalvarFoto();
        } catch (IOException ex) {
            Toast.makeText(this, "Não foi possível criar arquivo para foto", Toast.LENGTH_LONG).show();
        }
        if (arquivoDaFoto != null) {
            Uri fotoURI = FileProvider.getUriForFile(this,
                    "com.example.a02_listas.fileprovider",
                    arquivoDaFoto);
            intecaoAbrirCamera.putExtra(MediaStore.EXTRA_OUTPUT, fotoURI);
            startActivityForResult(intecaoAbrirCamera, 1);
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
//                Bundle extras = data.getExtras();
//                Bitmap bitmapaFoto = (Bitmap) extras.get("data");

                bar.setLogo(caminhoDaFoto);
                atualizaFotografiaNaTela();

            }
        }
    }

    private void atualizaFotografiaNaTela() {
        if (bar.getLogo() != null) {
            ImageView ivFotografia = findViewById(R.id.foto_local);
            ivFotografia.setImageURI(Uri.parse(bar.getLogo()));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == 1){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "GANHOU permissão", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(this, "Não GANHOU permissão", Toast.LENGTH_LONG).show();
            }
        }

    }

}
