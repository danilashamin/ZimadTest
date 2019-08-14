package ru.danilashamin.zimadtest.mvp.element;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ru.danilashamin.zimadtest.App;
import ru.danilashamin.zimadtest.R;
import ru.danilashamin.zimadtest.di.module.ElementFragmentModule;
import ru.danilashamin.zimadtest.model.Response;
import ru.danilashamin.zimadtest.navigation.BackButtonListener;
import ru.danilashamin.zimadtest.navigation.RouterProvider;
import ru.danilashamin.zimadtest.utils.DataType;

import static ru.danilashamin.zimadtest.utils.Constants.DATA;
import static ru.danilashamin.zimadtest.utils.Constants.DATA_TYPE;

public class ElementFragment extends Fragment implements ElementFragmentContract.View, BackButtonListener {

    @BindView(R.id.ivPhoto)
    AppCompatImageView ivPhoto;
    @BindView(R.id.tvNumber)
    AppCompatTextView tvNumber;
    @BindView(R.id.tvText)
    AppCompatTextView tvText;

    public static ElementFragment newInstance(Response.Data data, @DataType String dataType) {
        Bundle args = new Bundle();
        args.putSerializable(DATA, data);
        args.putString(DATA_TYPE, dataType);
        ElementFragment fragment = new ElementFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private Unbinder unbinder;
    private boolean mIsStateSaved;

    @Inject
    ElementFragmentContract.Presenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.INSTANCE.getInjectionManager().getElementfragmentComponent(getDataType(), new ElementFragmentModule(getData(),
                ((RouterProvider) getParentFragment()).getRouter())).inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_element, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        presenter.attachView(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        mIsStateSaved = false;
    }

    @Override
    public void onResume() {
        super.onResume();
        mIsStateSaved = false;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        mIsStateSaved = true;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (getActivity().isFinishing()) {
            presenter.destroy();
            App.INSTANCE.getInjectionManager().releaseDataFragmentComponent(getDataType());
            return;
        }


        if (mIsStateSaved) {
            mIsStateSaved = false;
            return;
        }

        boolean anyParentIsRemoving = false;
        Fragment parent = getParentFragment();
        while (!anyParentIsRemoving && parent != null) {
            anyParentIsRemoving = parent.isRemoving();
            parent = parent.getParentFragment();
        }

        if (isRemoving() || anyParentIsRemoving) {
            presenter.destroy();
            App.INSTANCE.getInjectionManager().releaseElementFragmentComponent(getDataType());

        }
    }

    private Response.Data getData() {
        return (Response.Data) getArguments().getSerializable(DATA);
    }

    private String getDataType() {
        return getArguments().getString(DATA_TYPE);
    }

    @Override
    public void setData(Response.Data data) {
        tvNumber.setText(String.valueOf(data.getNumber()));
        tvText.setText(data.getTitle());

        Glide.with(ivPhoto)
                .load(data.getPhotoUrl())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(ivPhoto);
    }

    @Override
    public boolean onBackPressed() {
        presenter.onBackPressed();
        return true;
    }
}
