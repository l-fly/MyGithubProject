package com.lib.common.mvp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewStub;
import com.jaeger.library.StatusBarUtil;
import com.lib.common.manager.ActivityManager;
import com.lib.common.view.LoadingInitView;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import immotor.com.lib_common.R;

public abstract class BaseActivity extends RxAppCompatActivity{
    private ViewStub mViewStubContent;
    private ViewStub mViewStubInitLoading;
    protected LoadingInitView mLoadingInitView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setTransparent(this);
        setContentView(R.layout.activity_root);
        initCommonView();
        initView();
        initListener();
        initData();
        ActivityManager.getInstance().addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManager.getInstance().finishActivity(this);
    }

    protected void initCommonView() {
        mViewStubContent = findViewById(R.id.view_stub_content);
        mViewStubInitLoading = findViewById(R.id.view_stub_init_loading);
        mViewStubContent.setLayoutResource(onBindLayout());
        mViewStubContent.inflate();
    }

    public abstract int onBindLayout();

    public abstract void initView();

    public abstract void initData();

    public void initListener() {
    }

    public void showInitLoadView() {
        showInitLoadView(true);
    }

    public void hideInitLoadView() {
        showInitLoadView(false);
    }

    private void showInitLoadView(boolean show) {
        if (mLoadingInitView == null) {
            View view = mViewStubInitLoading.inflate();
            mLoadingInitView = view.findViewById(R.id.view_init_loading);
        }
        mLoadingInitView.setVisibility(show ? View.VISIBLE : View.GONE);
        mLoadingInitView.loading(show);
    }
    public Context getContext() {
        return this;
    }
}
