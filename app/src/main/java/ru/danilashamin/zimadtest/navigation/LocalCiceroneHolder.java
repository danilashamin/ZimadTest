package ru.danilashamin.zimadtest.navigation;

import java.util.HashMap;

import ru.danilashamin.zimadtest.utils.DataType;
import ru.terrakok.cicerone.Cicerone;
import ru.terrakok.cicerone.Router;

public class LocalCiceroneHolder {
    private HashMap<String, Cicerone<Router>> container;

    public LocalCiceroneHolder() {
        container = new HashMap<>();
    }

    public Cicerone<Router> getCicerone(@DataType String containerTag) {
        if (!container.containsKey(containerTag)) {
            container.put(containerTag, Cicerone.create());
        }
        return container.get(containerTag);
    }
}