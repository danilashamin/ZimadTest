package ru.danilashamin.zimadtest.di;

import java.util.HashMap;
import java.util.Map;

import ru.danilashamin.zimadtest.di.component.AppComponent;
import ru.danilashamin.zimadtest.di.component.DaggerAppComponent;
import ru.danilashamin.zimadtest.di.component.DataFragmentComponent;
import ru.danilashamin.zimadtest.di.component.ElementFragmentComponent;
import ru.danilashamin.zimadtest.di.module.DataFragmentModule;
import ru.danilashamin.zimadtest.di.module.ElementFragmentModule;
import ru.danilashamin.zimadtest.utils.DataType;

public class InjectionManager {

    private AppComponent appComponent;

    private Map<String, DataFragmentComponent> dataFragmentComponentMap = new HashMap<>(); //Key - data type
    private Map<String, ElementFragmentComponent> elementFragmentComponentMap = new HashMap<>(); //Key - data type

    public InjectionManager() {
        appComponent = DaggerAppComponent.builder().build();
    }

    public DataFragmentComponent getDataFragmentComponent(@DataType String dataType, DataFragmentModule dataFragmentModule) {
        DataFragmentComponent component = dataFragmentComponentMap.get(dataType);
        if (component == null) {
            component = appComponent.dataFragmentComponentBuilder().dataFragmentModule(dataFragmentModule).build();
            dataFragmentComponentMap.put(dataType, component);
        }
        return component;
    }

    public ElementFragmentComponent getElementfragmentComponent(@DataType String dataType, ElementFragmentModule elementFragmentModule) {
        ElementFragmentComponent component = elementFragmentComponentMap.get(dataType);
        if (component == null) {
            component = appComponent.elementFragmentComponentBuilder().elementFragmentModule(elementFragmentModule).build();
            elementFragmentComponentMap.put(dataType, component);
        }
        return component;
    }

    public void releaseDataFragmentComponent(@DataType String dataType) {
        dataFragmentComponentMap.remove(dataType);
    }

    public void releaseElementFragmentComponent(@DataType String dataType){
        elementFragmentComponentMap.remove(dataType);
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
