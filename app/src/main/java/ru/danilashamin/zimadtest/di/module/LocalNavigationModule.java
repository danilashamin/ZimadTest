package ru.danilashamin.zimadtest.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.danilashamin.zimadtest.navigation.LocalCiceroneHolder;

@Module
public class LocalNavigationModule {
    @Provides
    @Singleton
    LocalCiceroneHolder provideLocalNavigationHolder() {
        return new LocalCiceroneHolder();
    }
}
