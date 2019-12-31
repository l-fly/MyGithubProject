package immotor.com.lib_common.mvptest.presenter;

import android.content.Context;

import immotor.com.lib_common.mvptest.contract.TestContract;

public class TestPresenter extends TestContract.Presenter {
    protected Context mContext;
    public TestPresenter(Context context){
        mContext = context;
    }
    @Override
    public void setName(final String name) {
        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(mView != null){
                    mView.setName(name);
                }

            }
        },1000 * 2);
    }

    @Override
    public void setAge(final int age) {
        if(mView != null){
            mView.showInitLoadView();
        }
        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(mView != null){
                    mView.setAge(age);
                    mView.hideInitLoadView();
                }
            }
        },1000 * 4);
    }

}
