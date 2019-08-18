package ru.danilashamin.zimadtest.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.danilashamin.zimadtest.interactor.MainInteractor;

@Module
public class InteractorModule {
    @Singleton
    @Provides
    MainInteractor provideMainInteractor() {
        return new MainInteractor();
    }
}
