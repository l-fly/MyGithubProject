package com.lib.common.mvp.presenter;

import android.content.Context;

public abstract class BasePresenter<V> {

    protected V mView;
    /**
     * 建立关联
     */
    public void attachView(V view) {
        this.mView = view;
    }
    /**
     * 断开关联
     */
    public void detachView() {
        mView = null;
    }



}
