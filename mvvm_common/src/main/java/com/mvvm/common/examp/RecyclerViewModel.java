package com.mvvm.common.examp;

import android.app.Application;
import android.databinding.ObservableArrayList;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mvvm.common.BR;
import com.mvvm.common.R;
import com.mvvm.common.base.BaseModel;
import com.mvvm.common.base.BaseViewModel;

import me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter;
import me.tatarka.bindingcollectionadapter2.ItemBinding;
import me.tatarka.bindingcollectionadapter2.OnItemBind;
import me.tatarka.bindingcollectionadapter2.collections.MergeObservableList;
import me.tatarka.bindingcollectionadapter2.itembindings.OnItemBindClass;

public class RecyclerViewModel extends BaseViewModel {
    public RecyclerViewModel(@NonNull Application application) {
        super(application);
        items.add(3);
        items.add(4);
        items.add(5);
    }

    public BindingRecyclerViewAdapter adapter = new BindingRecyclerViewAdapter<Object>();

   /* OnItemBindClass multipleItems = OnItemBindClass<Object>().apply {
        map<String>(BR.item, R.layout.item_header_footer)
        map<MutableItem>(BR.item, R.layout.item)
    }*/
   /*public OnItemBindClass multipleItems = new OnItemBindClass<Object>()
           .map(Integer.class, BR.item, R.layout.layout_view);*/

   public ItemBinding multipleItems = ItemBinding.of(new OnItemBind<Integer>() {
        @Override
        public void onItemBind(ItemBinding itemBinding, int position, Integer item) {
            if(item == 1){
                itemBinding.set(BR.item, R.layout.layout_view);
            }else {
                itemBinding.set(BR.item, R.layout.item_header_footer);
            }

            //通过item的类型, 动态设置Item加载的布局
           /* String itemType = (String) item.getItemType();
            if (MultiRecycleType_Head.equals(itemType)) {
                //设置头布局
                itemBinding.set(BR.viewModel, R.layout.item_multi_head);
            } else if (MultiRecycleType_Left.equals(itemType)) {
                //设置左布局
                itemBinding.set(BR.viewModel, R.layout.item_multi_rv_left);
            } else if (MultiRecycleType_Right.equals(itemType)) {
                //设置右布局
                itemBinding.set(BR.viewModel, R.layout.item_multi_rv_right);
            }*/
        }
    });

   public ObservableArrayList items = new ObservableArrayList<Integer>();
   public MergeObservableList headerFooterItems = new MergeObservableList<Object>()
            .insertItem(1)
            .insertList(items)
            .insertItem(2);


}
