package ru.danilashamin.zimadtest.di.component;

import javax.inject.Singleton;

import dagger.Component;
import ru.danilashamin.zimadtest.di.module.ApiModule;
import ru.danilashamin.zimadtest.di.module.GlobalNavigationModule;
import ru.danilashamin.zimadtest.di.module.LocalNavigationModule;
import ru.danilashamin.zimadtest.mvp.MainActivity;
import ru.danilashamin.zimadtest.mvp.data.DataPresenter;
import ru.danilashamin.zimadtest.mvp.element.ElementPresenter;
import ru.danilashamin.zimadtest.navigation.MainTabFragment;

@Singleton
@Component(modules = {ApiModule.class, GlobalNavigationModule.class, LocalNavigationModule.class})
public interface AppComponent {

    DataFragmentComponent.Builder dataFragmentComponentBuilder();

    ElementFragmentComponent.Builder elementFragmentComponentBuilder();

    void inject(MainTabFragment mainTabFragment);

    void inject(MainActivity mainActivity);

    void inject(DataPresenter dataPresenter);

    void inject(ElementPresenter elementPresenter);
}
