package immotor.com.mygithubproject.lean;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

public class LeanViewModel extends AndroidViewModel {
    public LeanViewModel(@NonNull Application application) {
        super(application);
    }
   /* public ObservableField<String> userName = new ObservableField<>("");
    public String getUserName() {
        return userName.get();
    }

    public void setUserName(String str) {
        userName.set(str);
    }*/
    String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
