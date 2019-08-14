package ru.danilashamin.zimadtest;

import android.content.Context;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

import ru.danilashamin.zimadtest.di.component.AppComponent;
import ru.danilashamin.zimadtest.di.component.DaggerAppComponent;

public class App extends MultiDexApplication {
    public static App INSTANCE;

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;

        appComponent = DaggerAppComponent.builder().build();

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
