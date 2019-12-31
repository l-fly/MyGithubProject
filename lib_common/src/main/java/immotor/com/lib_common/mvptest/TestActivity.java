package immotor.com.lib_common.mvptest;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.lib.common.mvp.BaseMvpActivity;
import immotor.com.lib_common.R;
import immotor.com.lib_common.mvptest.contract.TestContract;
import immotor.com.lib_common.mvptest.presenter.TestPresenter;

public class TestActivity extends BaseMvpActivity<TestPresenter> implements TestContract.View {
    TextView tvName,tvAge;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.i("###","onCreate");
        if(mPresenter != null){
            Log.i("###","mPresenter  " + mPresenter);
        }
    }

    @Override
    public TestPresenter initPresenter(Context context) {
        Log.i("###","initPresenter");
        return new TestPresenter(this);
    }

    @Override
    public int onBindLayout() {
        return R.layout.activity_test;
    }

    @Override
    public void initView() {
        tvName = findViewById(R.id.name);
        tvAge = findViewById(R.id.age);
    }

    @Override
    public void initData() {
        mPresenter.setName("AAA");

        mPresenter.setAge(99);

    }

    @Override
    public void setName(String name) {
        tvName.setText(name);
    }

    @Override
    public void setAge(int age) {
        tvAge.setText("" + age);
    }
}
