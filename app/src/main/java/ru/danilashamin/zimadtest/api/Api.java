package ru.danilashamin.zimadtest.api;

import io.reactivex.Observable;

import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.danilashamin.zimadtest.model.Response;
import ru.danilashamin.zimadtest.utils.DataType;

public interface Api {
    @GET("api.php")
    Observable<Response> requestData(@DataType @Query("query") String query);
}
