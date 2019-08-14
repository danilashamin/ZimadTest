package ru.danilashamin.zimadtest.di.module;

import dagger.Module;
import dagger.Provides;
import ru.danilashamin.zimadtest.di.scope.DataFragmentScope;
import ru.danilashamin.zimadtest.mvp.DataFragmentContract;
import ru.danilashamin.zimadtest.mvp.DataPresenter;
import ru.danilashamin.zimadtest.utils.DataType;

@Module
public class DataFragmentModule {

    @DataType
    private String dataType;

    public DataFragmentModule(@DataType String dataType) {
        this.dataType = dataType;
    }

    @DataFragmentScope
    @Provides
    DataFragmentContract.Presenter providesPresenter() {
        return new DataPresenter(dataType);
    }

}
