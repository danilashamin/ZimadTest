package ru.danilashamin.zimadtest.base.mvp;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class PresenterBase<T extends MvpView> implements MvpPresenter<T> {
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private T view;

    private boolean isFirstLaunch = true;

    @Override
    public void attachView(T mvpView) {
        view = mvpView;
        if(isFirstLaunch){
            isFirstLaunch = false;
            onFirstAttach();
        }
        onViewReady();
    }

    @Override
    public void detachView() {
        view = null;
    }

    public T getView() {
        return view;
    }

    protected boolean isViewAttached() {
        return view != null;
    }

    protected void disposeOnDestroy(Disposable d) {
        compositeDisposable.add(d);
    }

    public void onFirstAttach(){

    }

    @Override
    public void destroy() {
        compositeDisposable.clear();
    }
}