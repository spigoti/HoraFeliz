package com.felipe.horafeliz;
import android.app.Application;

import com.felipe.horafeliz.model.GerenciadorDeMigracoesRealm;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class NossaAplicacao extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);

        /**
         * Deleta os dados do Realm
         */
        Realm.deleteRealm(Realm.getDefaultConfiguration());

        /**
         * Migra o Realm para uma nova versão
         */
        RealmConfiguration configuracaoRealm = new RealmConfiguration.Builder()
                .schemaVersion(2)
                .migration( new GerenciadorDeMigracoesRealm() )
                .build();

        Realm.setDefaultConfiguration( configuracaoRealm );
        Realm.getInstance( configuracaoRealm );

    }
}
