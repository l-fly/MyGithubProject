package com.mvvm.common.examp;

import android.util.Log;

import com.mvvm.common.base.BaseModel;

public class MaimModel extends BaseModel {
    String name = "aaa";
    public String getName(){
        return name;
    }
    int age = 10;
    public int getAge(){
        return age;
    }

    @Override
    public void onCleared() {
        super.onCleared();
        name = "";
        age = 0;
        Log.i("#########","wwwwwwwwwwwwwww");
    }
}
