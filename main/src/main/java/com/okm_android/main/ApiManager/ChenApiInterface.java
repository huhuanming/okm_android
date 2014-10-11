package com.okm_android.main.ApiManager;

import com.okm_android.main.Model.RegisterBackData;
import com.okm_android.main.Model.RestaurantBackData;
import com.okm_android.main.Model.UploadBackData;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by chen on 14-7-28.
 */
public class ChenApiInterface {
    public interface ApiManagerVerificationCode {
        @GET("/users/mobile_verification_code")
        UploadBackData getVerificationCode(@Query("phone_number") String phone_number);
    }

    public interface ApiManagerCreateUser {
        @POST("/users")
        RegisterBackData createUser(@Query("phone_number") String phone_number, @Query("password") String password, @Query("encryption_code") String encryption_code);
    }

    public interface ApiManagerLogin {
        @POST("/users/login")
        RegisterBackData login(@Query("username") String username, @Query("password") String password);
    }

    public interface ApiManagerLoginByOauth {
        @POST("/users/login_by_oauth")
        RegisterBackData loginByOauth(@Query("uid") String uid, @Query("oauth_token") String oauth_token, @Query("oauth_type") String oauth_type);
    }

    public interface ApiManagerRestaurantsList {
        @GET("/restaurants")
        List<RestaurantBackData> RestaurantsList(@Query("latitude") String latitude, @Query("longitude") String longitude, @Query("page") String page);
    }
}
