package com.twisstosin.udacitybaking.Networking;

public class ApiUtils {

    private ApiUtils(){}

    //Udacity Recipes
    public static final String UDACITY_BASE_URL_MOVIE = "http://go.udacity.com/android-baking-app-json/";

    public static UdacityApiObservable getUdacityApiObservable() {
        return RetrofitClient.getUdacityClient(UDACITY_BASE_URL_MOVIE).create(UdacityApiObservable.class);
    }

}
