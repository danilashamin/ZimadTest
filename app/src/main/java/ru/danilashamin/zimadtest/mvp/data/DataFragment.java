package ru.danilashamin.zimadtest.mvp.data;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ru.danilashamin.zimadtest.App;
import ru.danilashamin.zimadtest.R;
import ru.danilashamin.zimadtest.di.module.DataFragmentModule;
import ru.danilashamin.zimadtest.model.Response;
import ru.danilashamin.zimadtest.navigation.BackButtonListener;
import ru.danilashamin.zimadtest.navigation.RouterProvider;
import ru.danilashamin.zimadtest.ui.DataAdapter;
import ru.danilashamin.zimadtest.utils.DataType;

import static ru.danilashamin.zimadtest.utils.Constants.CAT;
import static ru.danilashamin.zimadtest.utils.Constants.DATA_TYPE;

public class DataFragment extends Fragment implements DataFragmentContract.View, DataAdapter.DataAdapterListener, BackButtonListener {

    @Inject
    DataFragmentContract.Presenter presenter;

    private final DataAdapter dataAdapter = new DataAdapter(this);
    private Unbinder unbinder;

    @BindView(R.id.rvData)
    RecyclerView rvData;


    public static DataFragment newInstance(@DataType String dataType) {
        Bundle args = new Bundle();
        args.putString(DATA_TYPE, dataType);
        DataFragment fragment = new DataFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String dataType = getDataType();
        App.INSTANCE.getInjectionManager().getDataFragmentComponent(dataType, new DataFragmentModule(dataType,
                ((RouterProvider)getParentFragment()).getRouter())).inject(this);
    }

    @DataType
    private String getDataType() {
        String dataType = CAT;
        Bundle args = getArguments();
        if (args != null) {
            dataType = args.getString(DATA_TYPE);
        }
        return dataType;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data, container, false);
        unbinder = ButterKnife.bind(this, view);
        rvData.setLayoutManager(new LinearLayoutManager(getContext()));
        rvData.setAdapter(dataAdapter);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        presenter.attachView(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detachView();
        if (getActivity().isFinishing() || isRemoving()) {
            presenter.destroy();
            App.INSTANCE.getInjectionManager().releaseDataFragmentComponent(getDataType());
        }
    }

    @Override
    public void setData(List<Response.Data> data) {
        dataAdapter.setData(data);
    }

    @Override
    public void onDataElementClicked(Response.Data data) {
        presenter.onDataElementClicked(data);
    }

    @Override
    public boolean onBackPressed() {
        presenter.onBackPressed();
        return true;
    }
}
