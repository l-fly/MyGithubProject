package immotor.com.mygithubproject.fragment;

import android.app.Activity;
import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.orhanobut.logger.Logger;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import immotor.com.mygithubproject.R;
import immotor.com.mygithubproject.base.RxLazyFragment;
import immotor.com.mygithubproject.databinding.FragmentHomeMenuBinding;

public class HomeMenuFragment extends RxLazyFragment {
    FragmentHomeMenuBinding binding;
    RecyclerAdapter recyclerAdapter;
    String [] menus = {"启动其他应用","2","3"};
    public static HomeMenuFragment newInstance() {
        return new HomeMenuFragment();
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
                /*Intent it = new Intent();
                it.setClassName("immotor.com.arcface","immotor.com.arcface.MainActivity");
                it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); startActivity(it);
                it.putExtra("int",8);
                it.putExtra("msg","hoho");
                getSupportActivity().startActivity(it);*/

                Intent intent = new Intent("arcface.intent.action.activity");
                startActivityForResult(intent,1);
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            if(resultCode == Activity.RESULT_OK) {
                String result = data.getStringExtra("result");

                Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
            }
        }
    }
}
