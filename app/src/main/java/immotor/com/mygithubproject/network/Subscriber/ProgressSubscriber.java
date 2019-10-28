package immotor.com.mygithubproject.network.Subscriber;

import android.app.Dialog;
import android.content.Context;


import com.orhanobut.logger.Logger;

import immotor.com.mygithubproject.entity.RecommendBannerInfo;
import immotor.com.mygithubproject.entity.RecommendInfo;
import immotor.com.mygithubproject.widget.dialog.CommonDialog;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class ProgressSubscriber<T> implements Observer<T> {
    private Context context;
    private Dialog progressDialog;
    public ProgressSubscriber(Context context){
        this.context = context;
        initProgressDialog();
    }
    private void initProgressDialog(){
        if (progressDialog == null) {
            progressDialog = CommonDialog.createLoadingDialog(context, null);
            progressDialog.setCancelable(false);
        }
    }
    @Override
    public void onSubscribe(Disposable d) {
        if(progressDialog!=null){
            Logger.i("progressDialog");
            progressDialog.show();
        }
    }

    @Override
    public void onNext(T t) {
        Logger.i("onNext  " + ((RecommendBannerInfo)t).getCode());
    }

    @Override
    public void onError(Throwable e) {
        if(progressDialog!=null){
           // progressDialog.dismiss();
        }
    }

    @Override
    public void onComplete() {
        if(progressDialog!=null){
          //  progressDialog.dismiss();
        }
    }
}
