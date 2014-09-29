package com.okm_android.main.ApiManager;


import com.okm_android.main.Model.UploadBackData;

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


}
