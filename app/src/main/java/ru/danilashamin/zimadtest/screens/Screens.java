package ru.danilashamin.zimadtest.screens;

import ru.danilashamin.zimadtest.utils.DataType;
import ru.terrakok.cicerone.android.support.SupportAppScreen;

public final class Screens {
    public static final class TabScreen extends SupportAppScreen {
        private final String dataType;

        public TabScreen(@DataType String dataType) {
            this.dataType = dataType;
        }
    }
}
