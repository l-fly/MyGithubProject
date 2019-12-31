package immotor.com.lib_common.mvptest.contract;

import com.lib.common.mvp.presenter.BasePresenter;
import com.lib.common.mvp.view.BaseView;

public interface TestContract {
    interface View extends BaseView{
        void setName(String name);
        void setAge(int age);
    }
    abstract class Presenter extends BasePresenter<View>{
        public abstract void setName(String name);
        public abstract void setAge(int age);
    }
}
