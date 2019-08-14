package ru.danilashamin.zimadtest.di.component;

import dagger.Subcomponent;
import ru.danilashamin.zimadtest.di.module.ElementFragmentModule;
import ru.danilashamin.zimadtest.di.scope.ElementFragmentScope;
import ru.danilashamin.zimadtest.mvp.element.ElementFragment;

@Subcomponent(modules = ElementFragmentModule.class)
@ElementFragmentScope
public interface ElementFragmentComponent {

    void inject(ElementFragment elementFragment);

    @Subcomponent.Builder
    interface Builder {
        ElementFragmentComponent.Builder elementFragmentModule(ElementFragmentModule elementFragmentModule);

        ElementFragmentComponent build();
    }
}
