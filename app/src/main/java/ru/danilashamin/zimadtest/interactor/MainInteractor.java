package ru.danilashamin.zimadtest.interactor;

import java.util.ArrayList;
import java.util.List;

public class MainInteractor {

    private List<MainInteractorListener> listeners;

    public MainInteractor() {
        listeners = new ArrayList<>();
    }

    public void subscribe(MainInteractorListener listener) {
        listeners.add(listener);
    }

    public void unsubscribe(MainInteractorListener listener) {
        listeners.remove(listener);
    }

    public void notifyElementScreenOpened() {
        for (MainInteractorListener listener :
                listeners) {
            listener.onElementScreenOpened();
        }
    }

    public void notifyElementScreenClosed() {
        for (MainInteractorListener listener :
                listeners) {
            listener.onElementScreenClosed();
        }
    }

    public interface MainInteractorListener {
        void onElementScreenOpened();

        void onElementScreenClosed();
    }
}
