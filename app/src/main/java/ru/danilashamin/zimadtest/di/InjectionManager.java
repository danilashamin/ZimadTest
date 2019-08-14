package ru.danilashamin.zimadtest.di;

import java.util.HashMap;
import java.util.Map;

import ru.danilashamin.zimadtest.di.component.AppComponent;
import ru.danilashamin.zimadtest.di.component.DaggerAppComponent;
import ru.danilashamin.zimadtest.di.component.DataFragmentComponent;
import ru.danilashamin.zimadtest.di.module.DataFragmentModule;
import ru.danilashamin.zimadtest.utils.DataType;

public class InjectionManager {

    private AppComponent appComponent;

    private Map<String, DataFragmentComponent> components = new HashMap<>(); //Key - data type

    public InjectionManager() {
        appComponent = DaggerAppComponent.builder().build();
    }

    public DataFragmentComponent getDataFragmentComponent(@DataType String dataType, DataFragmentModule dataFragmentModule) {
        DataFragmentComponent component = components.get(dataType);
        if (component == null) {
            component = appComponent.dataFragmentComponentBuilder().dataFragmentModule(dataFragmentModule).build();
            components.put(dataType, component);
        }
        return component;
    }

    public void releaseDataFragmentComponent(@DataType String dataType) {
        components.remove(dataType);
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
