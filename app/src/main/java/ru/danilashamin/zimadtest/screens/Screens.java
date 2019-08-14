package ru.danilashamin.zimadtest.screens;

import androidx.fragment.app.Fragment;

import ru.danilashamin.zimadtest.model.Response;
import ru.danilashamin.zimadtest.mvp.data.DataFragment;
import ru.danilashamin.zimadtest.mvp.element.ElementFragment;
import ru.danilashamin.zimadtest.navigation.MainTabFragment;
import ru.danilashamin.zimadtest.utils.DataType;
import ru.terrakok.cicerone.android.support.SupportAppScreen;

public final class Screens {
    public static final class TabScreen extends SupportAppScreen {
        private final String dataType;

        public TabScreen(@DataType String dataType) {
            this.dataType = dataType;
        }

        @Override
        public Fragment getFragment() {
            return MainTabFragment.newInstance(dataType);
        }
    }

    public static final class DataScreen extends SupportAppScreen {
        private final String dataType;

        public DataScreen(String dataType) {
            this.dataType = dataType;
        }

        @Override
        public Fragment getFragment() {
            return DataFragment.newInstance(dataType);
        }
    }

    public static final class ElementScreen extends SupportAppScreen {
        private final Response.Data data;
        private final String dataType;

        public ElementScreen(Response.Data data, @DataType String dataType){
            this.data = data;
            this.dataType = dataType;
        }

        @Override
        public Fragment getFragment() {
            return ElementFragment.newInstance(data, dataType);
        }
    }
}
