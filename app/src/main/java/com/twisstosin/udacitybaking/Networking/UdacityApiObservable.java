package com.twisstosin.udacitybaking.Networking;

import com.twisstosin.udacitybaking.DataModel.Recipe;

import java.util.ArrayList;
import io.reactivex.Observable;
import retrofit2.http.GET;

public interface UdacityApiObservable {
    @GET(" ")
    Observable<ArrayList<Recipe>> getUdacityRecipeResult();
}
