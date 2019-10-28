package immotor.com.mygithubproject.fragment;


import android.databinding.ViewDataBinding;
import android.support.v4.view.ViewPager;
import immotor.com.mygithubproject.R;
import immotor.com.mygithubproject.adapter.HomePagerAdapter;
import immotor.com.mygithubproject.base.RxLazyFragment;
import immotor.com.mygithubproject.databinding.FragmentHomeBinding;

public class HomeFragment extends RxLazyFragment {
    FragmentHomeBinding fragmentHomeBinding;
    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    ViewPager mViewPager;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initViews(ViewDataBinding baseBinding) {
        fragmentHomeBinding = (FragmentHomeBinding)baseBinding;
        mViewPager = fragmentHomeBinding.viewPager;
        initViewPager();
    }

    private void initViewPager() {
        HomePagerAdapter mHomeAdapter = new HomePagerAdapter(getChildFragmentManager());
        fragmentHomeBinding.viewPager.setOffscreenPageLimit(5);
        fragmentHomeBinding.viewPager.setAdapter(mHomeAdapter);
        fragmentHomeBinding.slidingTabs.setViewPager(mViewPager);
        fragmentHomeBinding.viewPager.setCurrentItem(0);
    }

}
