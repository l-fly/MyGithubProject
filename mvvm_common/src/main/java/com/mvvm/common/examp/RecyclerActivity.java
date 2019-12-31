package com.mvvm.common.examp;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.mvvm.common.BR;
import com.mvvm.common.R;
import com.mvvm.common.base.BaseMvvmActivity;
import com.mvvm.common.databinding.ActivityRecyclerBinding;

import me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter;

public class RecyclerActivity extends BaseMvvmActivity<ActivityRecyclerBinding, RecyclerViewModel> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       /* LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        mBinding.rv.setLayoutManager(linearLayoutManager);
        mBinding.rv.setAdapter(new BindingRecyclerViewAdapter());

        View view = LayoutInflater.from(this).inflate(R.layout.layout_view,null);
        Log.i("#######","getChildCount    " + mBinding.rv.getChildCount());
        mBinding.rv.addView(view);
        View view2 = LayoutInflater.from(this).inflate(R.layout.layout_view,null);
        mBinding.rv.addView(view2);
        View view3 = LayoutInflater.from(this).inflate(R.layout.layout_view,null);
        mBinding.rv.addView(view3);
        Log.i("#######","getChildCount    " + mBinding.rv.getChildCount());

        mBinding.btn.setOnClickListener(v->{
            mBinding.rv.removeAllViews();
            View va = LayoutInflater.from(this).inflate(R.layout.layout_view,null);
            mBinding.rv.addView(va);
            Log.i("#######","getChildCount    " + mBinding.rv.getChildCount());
        });*/
    }

    @Override
    public int initContentView() {
        return R.layout.activity_recycler;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }
}
