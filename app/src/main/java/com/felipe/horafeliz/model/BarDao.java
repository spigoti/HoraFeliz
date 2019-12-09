package com.felipe.horafeliz.model;
import io.realm.Realm;
import io.realm.RealmResults;

import java.util.ArrayList;

public class BarDao {
    private ArrayList<Bar> bancoDeDados;
    private static BarDao INSTANCIA;

    public ArrayList<Bar> listarBares(){
        Realm realm = Realm.getDefaultInstance();
        RealmResults lista = realm.where(Bar.class).findAll();
        bancoDeDados.clear();
        bancoDeDados.addAll(realm.copyFromRealm(lista));
        return bancoDeDados;
    }

    public void adicionarBar(Bar bar){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealm(bar);
        realm.commitTransaction();
    }

    public int atualizaBarNaLista(Bar bar){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.insertOrUpdate(bar);
        realm.commitTransaction();

        for(int i = 0; i < bancoDeDados.size(); i++){
            if(bancoDeDados.get(i).getId().equals(bar.getId())){
                bancoDeDados.set(i, bar);
                return i;
            }
        }
        return -1; //
    }

    public int excluiBarDaLista(Bar bar){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.where(Bar.class).equalTo("id", bar.getId()).findFirst().deleteFromRealm();
        realm.commitTransaction();

        for(int i = 0; i < bancoDeDados.size(); i++){
            if(bancoDeDados.get(i).getId().equals(bar.getId())){
                bancoDeDados.remove(i);
                return i;
            }
        }
        return -1;
    }
    public Bar obterBarId(String id){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        Bar bar = realm.copyFromRealm(realm.where(Bar.class).equalTo("id", id).findFirst());
        realm.commitTransaction();
        return bar;
    }
    public static BarDao obterInstancia(){
        if (INSTANCIA == null){
            INSTANCIA = new BarDao();
        }
        return INSTANCIA;
    }
    private BarDao(){
        bancoDeDados = new ArrayList<Bar>();
    }

}
