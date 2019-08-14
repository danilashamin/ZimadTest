package ru.danilashamin.zimadtest;

import android.content.Context;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

import ru.danilashamin.zimadtest.di.InjectionManager;

public class App extends MultiDexApplication {
    public static App INSTANCE;

    private InjectionManager injectionManager;

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;

        injectionManager = new InjectionManager();

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public InjectionManager getInjectionManager() {
        return injectionManager;
    }
}
