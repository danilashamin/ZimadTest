package ru.danilashamin.zimadtest.mvp.element;

import ru.danilashamin.zimadtest.base.mvp.MvpPresenter;
import ru.danilashamin.zimadtest.base.mvp.MvpView;
import ru.danilashamin.zimadtest.model.Response;

public interface ElementFragmentContract {
    interface View extends MvpView {
        void setData(Response.Data data);
    }

    interface Presenter extends MvpPresenter<View> {
        void onBackPressed();
    }
}
