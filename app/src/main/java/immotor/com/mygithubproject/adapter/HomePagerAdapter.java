package immotor.com.mygithubproject.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import immotor.com.mygithubproject.fragment.HomeEmptyFragment;
import immotor.com.mygithubproject.fragment.HomeMenuFragment;

public class HomePagerAdapter extends FragmentPagerAdapter {
    private final String[] TITLES = {"example","empty","empty","empty"};
    private Fragment[] fragments;

    public HomePagerAdapter(FragmentManager fm) {
        super(fm);
        fragments = new Fragment[TITLES.length];
    }
    @Override
    public Fragment getItem(int position) {
        if (fragments[position] == null) {
            switch (position) {
                case 0:
                    fragments[position] = HomeMenuFragment.newInstance();
                    break;
                case 1:
                    fragments[position] = HomeEmptyFragment.newInstance();
                    break;
                case 2:
                    fragments[position] = HomeEmptyFragment.newInstance();
                    break;
                case 3:
                    fragments[position] = HomeEmptyFragment.newInstance();
                    break;
                default:
                    break;
            }
        }
        return fragments[position];
    }

    @Override
    public int getCount() {
        return TITLES.length;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return TITLES[position];
    }
}
