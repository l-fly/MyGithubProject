package immotor.com.mygithubproject.fragment;


import android.databinding.ViewDataBinding;
import immotor.com.mygithubproject.R;
import immotor.com.mygithubproject.base.RxLazyFragment;
import immotor.com.mygithubproject.databinding.FragmentHomeEmptyBinding;

public class HomeEmptyFragment extends RxLazyFragment {
    FragmentHomeEmptyBinding binding;

    public static HomeEmptyFragment newInstance() {
        return new HomeEmptyFragment();
    }
    @Override
    public int getLayoutResId() {
        return R.layout.fragment_home_empty;
    }

    @Override
    public void initViews(ViewDataBinding baseBinding) {
        binding = (FragmentHomeEmptyBinding) baseBinding;

    }


}
