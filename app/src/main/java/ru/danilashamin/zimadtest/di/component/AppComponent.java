package ru.danilashamin.zimadtest.di.component;

import javax.inject.Singleton;

import dagger.Component;
import ru.danilashamin.zimadtest.di.module.ApiModule;
import ru.danilashamin.zimadtest.mvp.MainActivity;

@Singleton
@Component(modules = ApiModule.class)
public interface AppComponent {
    void inject(MainActivity activity);
}
