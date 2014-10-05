package com.okm_android.main.ApiManager;

import com.okm_android.main.Model.RegisterBackData;
import com.okm_android.main.Model.UploadBackData;

import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by chen on 14-7-28.
 */
public class MerchantsApiInterface {
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
}
