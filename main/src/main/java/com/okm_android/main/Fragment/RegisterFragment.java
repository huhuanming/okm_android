package com.okm_android.main.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.okm_android.main.ApiManager.MainApiManager;
import com.okm_android.main.ApiManager.MerchantsApiManager;
import com.okm_android.main.Model.UploadBackData;
import com.okm_android.main.R;
import com.okm_android.main.Utils.Constant;
import com.okm_android.main.Utils.ErrorUtils;
import com.okm_android.main.Utils.ShareUtils;
import com.okm_android.main.Utils.ToastUtils;
import com.okm_android.main.Utils.TokenUtils.AccessToken;
import com.okm_android.main.View.Button.BootstrapButton;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import rx.android.concurrency.AndroidSchedulers;
import rx.util.functions.Action1;

/**
 * Created by chen on 14-9-23.
 */
public class RegisterFragment extends Fragment{
    private View parentView;
    private EditText phonenumber;
    private EditText register_verification_code;
    private TextView get_verification_code;
    private EditText password;
    private BootstrapButton register_button;
    private Handler handler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.fragment_register, container, false);

        init();
        initListener();

        handler = new Handler(){

            @Override
            public void handleMessage(Message msg) {
                // TODO Auto-generated method stub

                switch(msg.what)
                {
                    //获取成功
                    case Constant.MSG_SUCCESS:
                        get_verification_code.setEnabled(false);
                        get_verification_code.setText(msg.obj+"秒");
                        break;
                    case Constant.MSG_FINISH:
                        get_verification_code.setEnabled(true);
                        get_verification_code.setText("获取验证码");
                        break;
                }
                super.handleMessage(msg);
            }

        };
        return parentView;
    }

    private void init(){
        phonenumber = (EditText)parentView.findViewById(R.id.register_phonenumber);
        register_verification_code = (EditText)parentView.findViewById(R.id.register_verification_code);
        password = (EditText)parentView.findViewById(R.id.register_password);
        get_verification_code =(TextView)parentView.findViewById(R.id.get_verification_code);
        register_button = (BootstrapButton)parentView.findViewById(R.id.register_button);
    }

    private void initListener(){
        get_verification_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verificationCode();
                setTime();
//                swipeRefreshLayout.setRefreshing(true);
            }
        });
    }

    private void verificationCode()
    {

        AccessToken accessToken = new AccessToken(ShareUtils.getToken(getActivity()),ShareUtils.getKey(getActivity()));
        getVerificationCode("13281828743", new MainApiManager.FialedInterface() {
            @Override
            public void onSuccess(Object object) {

            }

            @Override
            public void onFailth(int code) {
                ErrorUtils.setError(code,getActivity());
            }

            @Override
            public void onOtherFaith() {
                ToastUtils.setToast(getActivity(), "发生错误");
            }

            @Override
            public void onNetworkError() {
                ToastUtils.setToast(getActivity(), "网络错误");
            }
        });
    }

    private void getVerificationCode(String phone_number, final MainApiManager.FialedInterface fialedInterface)
    {
        MerchantsApiManager.getMenuUploadBackData(phone_number).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<UploadBackData>() {
                    @Override
                    public void call(UploadBackData uploadBackData) {
                        fialedInterface.onSuccess(uploadBackData);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                        if(throwable.getClass().getName().toString().indexOf("RetrofitError") != -1) {
                            retrofit.RetrofitError e = (retrofit.RetrofitError) throwable;
                            if(e.isNetworkError())
                            {
                                fialedInterface.onNetworkError();

                            }
                            else {
                                fialedInterface.onFailth(e.getResponse().getStatus());
                            }
                        }
                        else{
                            fialedInterface.onOtherFaith();
                        }
                    }
                });
    }

    public  void setTime() {
        //min 1分钟
        int min=1;
        long start=System.currentTimeMillis();
        //end 计算结束时间
        final long end=start+min*60*1000;

        final Timer timer=new Timer();
        //延迟0毫秒（即立即执行）开始，每隔1000毫秒执行一次
        timer.schedule(new TimerTask()
        {
            public void run()
            {
                //show是剩余时间，即要显示的时间
                long show=end-System.currentTimeMillis();
                long s=show/1000%60;//秒
                // 获取一个Message对象，设置what为1
                Message msg = Message.obtain();
                msg.obj = s;
                msg.what = Constant.MSG_SUCCESS;
                // 发送这个消息到消息队列中
                handler.sendMessage(msg);
            }
        },0,1000);
        //计时结束时候，停止全部timer计时计划任务
        timer.schedule(new TimerTask()
        {
            public void run()
            {
                timer.cancel();

            }

        }, new Date(end));

    }
}
