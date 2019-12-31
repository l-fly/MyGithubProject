package com.lib.common.mvp.view;

import android.content.Context;


public interface BaseView{
    //显示初始加载的View，初始进来加载数据需要显示的View
    void showInitLoadView();
    //隐藏初始加载的View
    void hideInitLoadView();
    //加载失败，是否提示吐丝消息
    void onFaild(String msg, boolean showToast);
    
}
