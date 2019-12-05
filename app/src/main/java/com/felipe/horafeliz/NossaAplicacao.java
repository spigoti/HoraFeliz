package com.felipe.horafeliz;
import android.app.Application;

import io.realm.Realm;

public class NossaAplicacao extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //Realm.init(this);
//        Realm.deleteRealm(Realm.getDefaultConfiguration());
    }
}
