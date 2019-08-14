package ru.danilashamin.zimadtest.mvp.element;

import ru.danilashamin.zimadtest.App;
import ru.danilashamin.zimadtest.base.mvp.PresenterBase;
import ru.danilashamin.zimadtest.model.Response;
import ru.terrakok.cicerone.Router;

public class ElementPresenter extends PresenterBase<ElementFragmentContract.View> implements ElementFragmentContract.Presenter {

    Router router;

    private final Response.Data data;

    public ElementPresenter(Response.Data data, Router router) {
        this.data = data;
        this.router = router;
        App.INSTANCE.getInjectionManager().getAppComponent().inject(this);
    }

    @Override
    public void onBackPressed() {
        router.exit();
    }

    @Override
    public void onViewReady() {
        getView().setData(data);
    }
}
