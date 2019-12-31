package com.mvvm.common.bindingAdapter;


import android.view.View;

/**

 * 执行的命令回调, 用于ViewModel与xml之间的数据绑定
 */
public class BindingClickInterval {
    private BindingAction execute;

    public BindingClickInterval(BindingAction execute) {
        this.execute = execute;
    }
    /**
     * 执行BindingAction命令
     */
    public void execute(View view) {
        if (execute != null ) {
            execute.onClick(view);
        }
    }
    public interface BindingAction {
        void onClick(View view);
    }
}
