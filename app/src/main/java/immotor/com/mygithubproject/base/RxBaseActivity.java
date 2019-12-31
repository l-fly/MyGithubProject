package immotor.com.mygithubproject.base;

import android.app.Dialog;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;

import com.jaeger.library.StatusBarUtil;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import immotor.com.mygithubproject.widget.dialog.CommonDialog;


/**
 * Activity基类
 */
public abstract class RxBaseActivity extends RxAppCompatActivity {
    public Dialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setTransparent(this);
        //设置布局内容
        ViewDataBinding baseBinding = DataBindingUtil.setContentView(this,getLayoutId());
        //初始化控件
        initViews(baseBinding);
        //初始化ToolBar
        initToolBar();
    }


    /**
     * 显示加载进度条
     */
    protected void showProgressDialog(){
        if (progressDialog == null) {
            progressDialog = CommonDialog.createLoadingDialog(this, null);
            progressDialog.setCancelable(false);
        }
        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
    }
    /**
     * 隐藏加载进度条
     */
    protected void hideProgressDialog(){
        if (progressDialog != null&& progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
    /**
     * 设置布局layout
     *
     * @return
     */
    public abstract int getLayoutId();

    /**
     * 初始化views
     *
     * @param baseBinding
     */
    public abstract void initViews(ViewDataBinding baseBinding);

    /**
     * 初始化toolbar
     */
    public abstract void initToolBar();

    /**
     * 加载数据
     */
    public void loadData() {
    }

    /**
     * 初始化recyclerView
     */
    public void initRecyclerView() {
    }

    /**
     * 初始化refreshLayout
     */
    public void initRefreshLayout() {
    }

    /**
     * 设置数据显示
     */
    public void finishTask() {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
