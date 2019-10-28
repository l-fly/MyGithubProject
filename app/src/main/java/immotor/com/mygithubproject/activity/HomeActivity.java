package immotor.com.mygithubproject.activity;


import android.databinding.ViewDataBinding;
import android.os.Bundle;

import com.orhanobut.logger.Logger;

import java.util.concurrent.TimeUnit;

import immotor.com.mygithubproject.R;
import immotor.com.mygithubproject.base.RxBaseActivity;
import immotor.com.mygithubproject.databinding.ActivityHomeBinding;
import immotor.com.mygithubproject.entity.RecommendBannerInfo;
import immotor.com.mygithubproject.fragment.HomeFragment;
import immotor.com.mygithubproject.network.RetrofitHelper;
import immotor.com.mygithubproject.network.Subscriber.ProgressSubscriber;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class HomeActivity extends RxBaseActivity {
    ActivityHomeBinding activityHomeBinding;
    HomeFragment homeFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    public void initViews(ViewDataBinding baseBinding) {
        activityHomeBinding = (ActivityHomeBinding)baseBinding;

        homeFragment = HomeFragment.newInstance();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, homeFragment)
                .show(homeFragment).commit();
        initProgressDialog();

       /* new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    Logger.i("Thread  : " + Thread.currentThread().getName());
                    subscribe不在UI线程
                    loadData();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();*/
        Observable.timer(3, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(o ->{
                    Logger.i("Thread  : " + Thread.currentThread().getName());
                    loadData();
                });
    }

    @Override
    public void initToolBar() {

    }

    @Override
    public void loadData() {
        RetrofitHelper.getBiliAppAPI()
                .getRecommendedBannerInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(recommendBannerInfo -> {
                    Logger.i("" + recommendBannerInfo.getCode());
                },throwable -> {
                    dismissProgressDialog();
                },()->{
                    dismissProgressDialog();
                },onSubscribe->{
                    Logger.i("onSubscribe" );
                    Logger.i("currentThread name: " + Thread.currentThread().getName());

                    showProgressDialog();
                });
    }
}
