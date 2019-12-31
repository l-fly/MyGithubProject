package com.mvvm.common.examp;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.databinding.ObservableList;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.mvvm.common.R;
import com.mvvm.common.base.BaseViewModel;
import com.mvvm.common.bindingAdapter.BindingClickInterval;


import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.internal.operators.observable.ObservableCollect;

public class MainViewModel extends BaseViewModel<MaimModel> {
    public int placeholderRes = R.mipmap.ic_launcher_round;

    public ObservableInt age;
    /*public MainViewModel(@NonNull Application application) {
        super(application);
        name = model.getName();
        age = model.getAge();

    }*/
    public MainViewModel(@NonNull Application application, MaimModel model) {
        super(application, model);
        name = new ObservableField<String>();
        name.set(model.getName());
        age = new ObservableInt();
        age.set(3);
        names = new ObservableArrayList<>();
        names.add("bbb");



    }

    public ObservableList<String> names;

    public ObservableField<String> name;
    @MainThread
    public void ab(){
        name.set(name.get()+"b");
    }
    public BindingClickInterval onClickInterval = new BindingClickInterval(new BindingClickInterval.BindingAction() {
        @Override
        public void onClick(View v) {
            Log.i("########" ,"onClickInterval");
            age.set(age.get() + 3);
            names.remove(0);
            names.add("ddddd");
            ab();
           // name.setValue("bbb");
           // startActivity(RecyclerActivity.class);
        }
    });

    public View.OnClickListener loginOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.i("########" ,"loginOnClick");
        }
    };


}
