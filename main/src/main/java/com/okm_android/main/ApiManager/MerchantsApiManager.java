package com.okm_android.main.ApiManager;


import com.okm_android.main.Model.RegisterBackData;
import com.okm_android.main.Model.RestaurantBackData;
import com.okm_android.main.Model.UploadBackData;

import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.concurrency.Schedulers;
import rx.subscriptions.Subscriptions;

/**
 * Created by chen on 14-7-19.
 */
public class MerchantsApiManager extends MainApiManager{

    private static final MerchantsApiInterface.ApiManagerVerificationCode VerificationCodeapiManager = restAdapter.create(MerchantsApiInterface.ApiManagerVerificationCode.class);
    public static Observable<UploadBackData> getMenuUploadBackData(final String phone_number) {
        return Observable.create(new Observable.OnSubscribeFunc<UploadBackData>() {
            @Override
            public Subscription onSubscribe(Observer<? super UploadBackData> observer) {
                try {
                    observer.onNext(VerificationCodeapiManager.getVerificationCode(phone_number));
                    observer.onCompleted();
                } catch (Exception e) {
                    observer.onError(e);
                }

                return Subscriptions.empty();
            }
        }).subscribeOn(Schedulers.threadPoolForIO());
    }

    private static final MerchantsApiInterface.ApiManagerCreateUser CreateUserapiManager = restAdapter.create(MerchantsApiInterface.ApiManagerCreateUser.class);
    public static Observable<RegisterBackData> createUser(final String phone_number, final String password, final String encryption_code) {
        return Observable.create(new Observable.OnSubscribeFunc<RegisterBackData>() {
            @Override
            public Subscription onSubscribe(Observer<? super RegisterBackData> observer) {
                try {
                    observer.onNext(CreateUserapiManager.createUser(phone_number, password, encryption_code));
                    observer.onCompleted();
                } catch (Exception e) {
                    observer.onError(e);
                }

                return Subscriptions.empty();
            }
        }).subscribeOn(Schedulers.threadPoolForIO());
    }

    private static final MerchantsApiInterface.ApiManagerLogin LoginapiManager = restAdapter.create(MerchantsApiInterface.ApiManagerLogin.class);
    public static Observable<RegisterBackData> Login(final String phone_number, final String password) {
        return Observable.create(new Observable.OnSubscribeFunc<RegisterBackData>() {
            @Override
            public Subscription onSubscribe(Observer<? super RegisterBackData> observer) {
                try {
                    observer.onNext(LoginapiManager.login(phone_number, password));
                    observer.onCompleted();
                } catch (Exception e) {
                    observer.onError(e);
                }

                return Subscriptions.empty();
            }
        }).subscribeOn(Schedulers.threadPoolForIO());
    }

    private static final MerchantsApiInterface.ApiManagerLoginByOauth LoginByOauthapiManager = restAdapter.create(MerchantsApiInterface.ApiManagerLoginByOauth.class);
    public static Observable<RegisterBackData> LoginByOauth(final String uid, final String oauth_token, final String oauth_type) {
        return Observable.create(new Observable.OnSubscribeFunc<RegisterBackData>() {
            @Override
            public Subscription onSubscribe(Observer<? super RegisterBackData> observer) {
                try {
                    observer.onNext(LoginByOauthapiManager.loginByOauth(uid, oauth_token,oauth_type));
                    observer.onCompleted();
                } catch (Exception e) {
                    observer.onError(e);
                }

                return Subscriptions.empty();
            }
        }).subscribeOn(Schedulers.threadPoolForIO());
    }

    private static final MerchantsApiInterface.ApiManagerRestaurantsList RestaurantsListapiManager = restAdapter.create(MerchantsApiInterface.ApiManagerRestaurantsList.class);
    public static Observable<List<RestaurantBackData>> RestaurantsList(final String latitude, final String longitude, final String page) {
        return Observable.create(new Observable.OnSubscribeFunc<List<RestaurantBackData>>() {
            @Override
            public Subscription onSubscribe(Observer<? super List<RestaurantBackData>> observer) {
                try {
                    observer.onNext(RestaurantsListapiManager.RestaurantsList(latitude, longitude,page));
                    observer.onCompleted();
                } catch (Exception e) {
                    observer.onError(e);
                }

                return Subscriptions.empty();
            }
        }).subscribeOn(Schedulers.threadPoolForIO());
    }


}
