package immotor.com.mygithubproject.lean;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.databinding.library.baseAdapters.BR;

import immotor.com.mygithubproject.R;
import immotor.com.mygithubproject.databinding.ActivityViewModelLeanBinding;

public class ViewModelLeanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityViewModelLeanBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_view_model_lean);
        LeanViewModel leanViewModel = new LeanViewModel(getApplication());
        leanViewModel.setUserName("aaaaaaa");
        //binding.setViewModel(leanViewModel);
        binding.setVariable(BR.viewModel,leanViewModel);
        leanViewModel.setUserName("bbbbb");
        leanViewModel.setAge(10);

    }
}
