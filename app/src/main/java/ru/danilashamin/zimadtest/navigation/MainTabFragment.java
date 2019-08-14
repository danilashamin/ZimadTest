package ru.danilashamin.zimadtest.navigation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import javax.inject.Inject;

import ru.danilashamin.zimadtest.App;
import ru.danilashamin.zimadtest.R;
import ru.danilashamin.zimadtest.screens.Screens;
import ru.danilashamin.zimadtest.utils.DataType;
import ru.terrakok.cicerone.Cicerone;
import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.Router;
import ru.terrakok.cicerone.Screen;
import ru.terrakok.cicerone.android.support.SupportAppNavigator;

import static ru.danilashamin.zimadtest.utils.Constants.TAB_SCREEN_TYPE;

public class MainTabFragment extends Fragment implements RouterProvider, BackButtonListener {

    @Inject
    LocalCiceroneHolder ciceroneHolder;

    private Navigator navigator;

    public static MainTabFragment newInstance(@DataType String dataType) {
        Bundle args = new Bundle();
        args.putSerializable(TAB_SCREEN_TYPE, dataType);
        MainTabFragment fragment = new MainTabFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @DataType
    private String getTabScreenType() {
        return getArguments().getString(TAB_SCREEN_TYPE);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        App.INSTANCE.getInjectionManager().getAppComponent().inject(this);
        super.onCreate(savedInstanceState);
    }

    private Cicerone<Router> getCicerone() {
        return ciceroneHolder.getCicerone(getTabScreenType());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main_tab, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        getCicerone().getNavigatorHolder().setNavigator(getNavigator());
    }

    @Override
    public void onPause() {
        getCicerone().getNavigatorHolder().removeNavigator();
        super.onPause();
    }

    private Navigator getNavigator() {
        if (navigator == null) {
            navigator = new SupportAppNavigator(getActivity(), getChildFragmentManager(), R.id.main_tab_container);
        }
        return navigator;
    }

    @Override
    public Router getRouter() {
        return getCicerone().getRouter();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Screen screen = new Screens.DataScreen(getTabScreenType());
        if (getChildFragmentManager().findFragmentById(R.id.main_tab_container) == null) {
            getCicerone().getRouter().replaceScreen(screen);
        }
    }

    @Override
    public boolean onBackPressed() {
        Fragment fragment = getChildFragmentManager().findFragmentById(R.id.main_tab_container);
        if (fragment instanceof BackButtonListener
                && ((BackButtonListener) fragment).onBackPressed()) {
            return true;
        } else {
            ((RouterProvider) getActivity()).getRouter().exit();
            return true;
        }
    }

}
