package com.mvvm.common.examp;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mvvm.common.BR;
import com.mvvm.common.R;
import com.mvvm.common.base.BaseActivity;
import com.mvvm.common.base.BaseMvvmActivity;
import com.mvvm.common.base.BaseViewModel;
import com.mvvm.common.bindingAdapter.BindingClickInterval;
import com.mvvm.common.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseMvvmActivity <ActivityMainBinding, MainViewModel> {
    private static String[] PERMISSIONS_STORAGE = {"android.permission.RECEIVE_SMS",
            "android.permission.READ_SMS" };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //mBinding.setVariable(BR.userList, mViewModel.names);
       // mBinding.setUserList(mViewModel.names);
        /*mViewModel.names.remove(0);
        mViewModel.names.add("cccc");*/
        String imageUrl="https://www.baidu.com/img/bd_logo1.png";
        mBinding.setImgurl(imageUrl);
        //mBinding.setName(mViewModel.name);

      /*  mViewModel.name.observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                Log.e("####", "onChanged: 数据有更新  "  + s);
            }
        });*/
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int permission1 = ActivityCompat.checkSelfPermission(this,
                    PERMISSIONS_STORAGE[0]);
            int permission2 = ActivityCompat.checkSelfPermission(this,
                    PERMISSIONS_STORAGE[1]);
            if (permission1 != PackageManager.PERMISSION_GRANTED && permission2 != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE,123);
            }
        }


        SmsReceiver smsReceiver = new SmsReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(SMS_RECEIVED_ACTION);
        registerReceiver(smsReceiver, filter);
    }

    @Override
    public int initContentView() {
        return R.layout.activity_main;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public MainViewModel initViewModel() {

        return new MainViewModel(getApplication(),new MaimModel());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    private static final String SMS_RECEIVED_ACTION = "android.provider.Telephony.SMS_RECEIVED";
    class SmsReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i("#### SMS  ","you xinxi");
            String action = intent.getAction();
            //判断广播消息
            if (action.equals(SMS_RECEIVED_ACTION)){
                Bundle bundle = intent.getExtras();
                //如果不为空
                if (bundle!=null){
                    //将pdus里面的内容转化成Object[]数组
                    Object pdusData[] = (Object[]) bundle.get("pdus");// pdus ：protocol data unit  ：
                    //解析短信
                    SmsMessage[] msg = new SmsMessage[pdusData.length];
                    for (int i = 0;i < msg.length;i++){
                        byte pdus[] = (byte[]) pdusData[i];
                        msg[i] = SmsMessage.createFromPdu(pdus);
                    }
                    StringBuffer content = new StringBuffer();//获取短信内容
                    StringBuffer phoneNumber = new StringBuffer();//获取地址
                    //分析短信具体参数
                    for (SmsMessage temp : msg){
                        content.append(temp.getMessageBody());
                        phoneNumber.append(temp.getOriginatingAddress());
                    }
                    Log.i("#### SMS  ","发送者号码：" + phoneNumber.toString() + "  短信内容：" + content.toString());

                    mViewModel.name.set("发送者号码：" + phoneNumber.toString() + "  短信内容：" + content.toString());

                    //test

                }
            }
        }
    }
}
