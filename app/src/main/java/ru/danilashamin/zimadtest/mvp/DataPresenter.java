package ru.danilashamin.zimadtest.mvp;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ru.danilashamin.zimadtest.App;
import ru.danilashamin.zimadtest.api.Api;
import ru.danilashamin.zimadtest.base.mvp.PresenterBase;
import ru.danilashamin.zimadtest.model.Response;
import ru.danilashamin.zimadtest.utils.DataType;
import ru.terrakok.cicerone.Router;

public class DataPresenter extends PresenterBase<DataFragmentContract.View> implements DataFragmentContract.Presenter {

    @Inject
    Router router;

    @Inject
    Api api;

    @DataType
    private final String dataType;

    private List<Response.Data> data;

    public DataPresenter(@DataType String dataType) {
        this.dataType = dataType;
        App.INSTANCE.getInjectionManager().getAppComponent().inject(this);
    }

    @Override
    public void onFirstAttach() {
        disposeOnDestroy(api.requestData(dataType)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    data = response.getData();
                    getView().setData(data);
                }));
    }

    @Override
    public void onViewReady() {
        if (data != null) {
            getView().setData(data);
        }
    }

    @Override
    public void onDataElementClicked(Response.Data data) {

    }

    @Override
    public void onBackPressed() {
        router.exit();
    }
}
