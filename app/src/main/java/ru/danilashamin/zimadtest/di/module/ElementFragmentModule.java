package ru.danilashamin.zimadtest.di.module;

import dagger.Module;
import dagger.Provides;
import ru.danilashamin.zimadtest.di.scope.ElementFragmentScope;
import ru.danilashamin.zimadtest.model.Response;
import ru.danilashamin.zimadtest.mvp.element.ElementFragmentContract;
import ru.danilashamin.zimadtest.mvp.element.ElementPresenter;
import ru.terrakok.cicerone.Router;

@Module
public class ElementFragmentModule {

    private final Response.Data data;
    private final Router router;

    public ElementFragmentModule(Response.Data data, Router router) {
        this.data = data;
        this.router = router;
    }

    @ElementFragmentScope
    @Provides
    ElementFragmentContract.Presenter providePresenter() {
        return new ElementPresenter(data, router);
    }
}
