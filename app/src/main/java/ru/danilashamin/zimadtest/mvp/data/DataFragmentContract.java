package ru.danilashamin.zimadtest.mvp.data;

import java.util.List;

import ru.danilashamin.zimadtest.base.mvp.MvpPresenter;
import ru.danilashamin.zimadtest.base.mvp.MvpView;
import ru.danilashamin.zimadtest.model.Response;

public interface DataFragmentContract {
    interface View extends MvpView {
        void setData(List<Response.Data> data);
    }

    interface Presenter extends MvpPresenter<View> {
        void onDataElementClicked(Response.Data data);
        void onBackPressed();
    }
}
