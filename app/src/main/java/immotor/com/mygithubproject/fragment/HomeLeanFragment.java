package immotor.com.mygithubproject.fragment;

import android.app.Activity;
import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.Arrays;
import java.util.List;

import immotor.com.mygithubproject.R;
import immotor.com.mygithubproject.base.RxLazyFragment;
import immotor.com.mygithubproject.databinding.FragmentHomeMenuBinding;
import immotor.com.mygithubproject.lean.ViewModelLeanActivity;

public class HomeLeanFragment extends RxLazyFragment {
    FragmentHomeMenuBinding binding;
    RecyclerAdapter recyclerAdapter;
    String [] menus = {"ViewModelLean","2","3"};
    public static HomeLeanFragment newInstance() {
        return new HomeLeanFragment();
    }
    @Override
    public int getLayoutResId() {
        return R.layout.fragment_home_menu;
    }

    @Override
    public void initViews(ViewDataBinding baseBinding) {
        binding = (FragmentHomeMenuBinding) baseBinding;
        initRecyclerView();
    }

    @Override
    protected void initRecyclerView() {
        recyclerAdapter = new RecyclerAdapter(R.layout.layout_menu_item);
        binding.recyclerView.addItemDecoration(new DividerItemDecoration(
                getApplicationContext(), DividerItemDecoration.VERTICAL));
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        binding.recyclerView.setAdapter(recyclerAdapter);

        List<String> list = Arrays.asList(menus);
        recyclerAdapter.addData(list);
        recyclerAdapter.notifyDataSetChanged();
        recyclerAdapter.setOnItemClickListener(((adapter, view, position) -> {
            if(position == 0){

                Intent intent = new Intent(getContext(), ViewModelLeanActivity.class);
                startActivity(intent);
            }
        }));
    }

    class RecyclerAdapter extends BaseQuickAdapter<String, BaseViewHolder> {


        public RecyclerAdapter(int layoutResId) {
            super(layoutResId);
        }

        @Override
        protected void convert(@NonNull BaseViewHolder helper, String item) {
            helper.setText(R.id.text,item);
        }
    }

}
