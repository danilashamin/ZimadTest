package ru.danilashamin.zimadtest.di.component;

import dagger.Subcomponent;
import ru.danilashamin.zimadtest.di.module.DataFragmentModule;
import ru.danilashamin.zimadtest.di.scope.DataFragmentScope;
import ru.danilashamin.zimadtest.mvp.data.DataFragment;

@Subcomponent(modules = DataFragmentModule.class)
@DataFragmentScope
public interface DataFragmentComponent {

    void inject(DataFragment dataFragment);

    @Subcomponent.Builder
    interface Builder {
        DataFragmentComponent.Builder dataFragmentModule(DataFragmentModule dataFragmentModule);

        DataFragmentComponent build();
    }
}
