package ru.danilashamin.zimadtest.base.mvp;

public interface MvpPresenter<V extends MvpView> {

    void attachView(V mvpView);

    void onFirstAttach();

    void onViewReady();

    void detachView();

    void destroy();
}