package com.felipe.horafeliz;
import io.realm.Realm;

public class NossaAplicacao {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
//        Realm.deleteRealm(Realm.getDefaultConfiguration());
    }
}
