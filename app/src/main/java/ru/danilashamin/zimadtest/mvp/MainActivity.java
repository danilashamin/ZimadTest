package ru.danilashamin.zimadtest.mvp;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.danilashamin.zimadtest.App;
import ru.danilashamin.zimadtest.R;
import ru.danilashamin.zimadtest.navigation.RouterProvider;
import ru.danilashamin.zimadtest.screens.Screens;
import ru.danilashamin.zimadtest.utils.Constants;
import ru.danilashamin.zimadtest.utils.DataType;
import ru.terrakok.cicerone.Router;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, RouterProvider {
    @Inject
    Router router;

    @BindView(R.id.bottomNavigationView)
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        App.INSTANCE.getInjectionManager().getAppComponent().inject(this);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        if (savedInstanceState == null) {
            bottomNavigationView.setSelectedItemId(R.id.first_tab);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.first_tab:
                return selectTab(Constants.CAT);
            case R.id.second_tab:
                return selectTab(Constants.DOG);
            default:
                return false;
        }
    }

    private boolean selectTab(@DataType String dataType) {
        FragmentManager fm = getSupportFragmentManager();
        Fragment currentFragment = null;
        List<Fragment> fragments = fm.getFragments();
        for (Fragment f : fragments) {
            if (f.isVisible()) {
                currentFragment = f;
                break;
            }
        }
        Fragment newFragment = fm.findFragmentByTag(dataType);

        if (newFragment != null && currentFragment == newFragment)
            return false;

        FragmentTransaction transaction = fm.beginTransaction();
        if (newFragment == null) {
            transaction.add(R.id.mainContainer, new Screens.TabScreen(dataType).getFragment(), dataType);
        }

        if (currentFragment != null) {
            transaction.hide(currentFragment);
        }

        if (newFragment != null) {
            transaction.show(newFragment);
        }
        transaction.commitNow();

        return true;
    }

    @Override
    public Router getRouter() {
        return router;
    }
}
