package com.lib.common.mvp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.lib.common.mvp.presenter.BasePresenter;
import com.lib.common.mvp.view.BaseView;


public abstract class BaseMvpActivity<P extends BasePresenter> extends BaseActivity implements BaseView {
    protected P mPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mPresenter = initPresenter(this);
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        super.onCreate(savedInstanceState);

    }
    public abstract P initPresenter(Context context);

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mPresenter != null){
            mPresenter.detachView();
            mPresenter = null;
        }
    }
    @Override
    public void onFaild(String msg, boolean showToast) {

    }
}
