package com.mvvm.common.bindingAdapter;

import android.databinding.BindingAdapter;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.concurrent.TimeUnit;

import io.reactivex.functions.Consumer;

public class Adapter {
    //防重复点击间隔
    public static final int CLICK_INTERVAL = 500;
    @BindingAdapter("onClickInterval")
    public static void onClickInterval(final View view, final BindingClickInterval clickCommand) {
        RxView.clicks(view)
                .throttleFirst(CLICK_INTERVAL, TimeUnit.MILLISECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object object) throws Exception {
                        if (clickCommand != null) {
                            clickCommand.execute(view);
                        }
                    }
                });
    }

    @BindingAdapter(value = {"imageUrl", "placeholderRes"}, requireAll = false)
    public static void setImageUrl(ImageView imageView, String url, int placeholderRes) {
        if (!TextUtils.isEmpty(url)) {
            //使用Glide框架加载图片
            Glide.with(imageView.getContext())
                    .load(url)
                    .apply(new RequestOptions().placeholder(placeholderRes))
                    .into(imageView);
        }
    }
}
