package ru.danilashamin.zimadtest.di.module;

import dagger.Module;
import dagger.Provides;
import ru.danilashamin.zimadtest.di.scope.DataFragmentScope;
import ru.danilashamin.zimadtest.mvp.data.DataFragmentContract;
import ru.danilashamin.zimadtest.mvp.data.DataPresenter;
import ru.danilashamin.zimadtest.utils.DataType;
import ru.terrakok.cicerone.Router;

@Module
public class DataFragmentModule {

    @DataType
    private String dataType;
    private final Router router;

    public DataFragmentModule(@DataType String dataType, Router router) {
        this.dataType = dataType;
        this.router = router;
    }

    @DataFragmentScope
    @Provides
    DataFragmentContract.Presenter providesPresenter() {
        return new DataPresenter(dataType, router);
    }

}
