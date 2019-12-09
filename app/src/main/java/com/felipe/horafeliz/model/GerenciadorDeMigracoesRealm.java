package com.felipe.horafeliz.model;

import android.util.Log;

import io.realm.DynamicRealm;
import io.realm.RealmMigration;
import io.realm.RealmObjectSchema;
import io.realm.RealmSchema;

public class GerenciadorDeMigracoesRealm implements RealmMigration {
    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
        for(long versao = oldVersion; versao < newVersion; versao++){
            Log.d("MIGRACOES", "Migrando da "+oldVersion+" para "+newVersion);
            passoDeMigracao(realm, versao, versao + 1);
        }
    }

    private void passoDeMigracao(DynamicRealm realm, long versaoVelha, long versaoNova){

        /**
         * Adiciona uma nova versão do Realm, contendo latitude e longitude
         */
        if(versaoVelha == 1 && versaoNova == 2){
            RealmSchema schema = realm.getSchema();
            RealmObjectSchema compromissoSchema = schema.get("Bar");
            compromissoSchema.addField( "latitude", Double.class );
            compromissoSchema.addField( "longitude", Double.class );
        }
    }

}
