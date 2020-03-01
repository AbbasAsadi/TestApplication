package com.example.testapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testapplication.databinding.ListItemBinding;
import com.example.testapplication.model.ResponseBody;
import com.example.testapplication.viewModel.ItemViewModel;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private Context mContext;
    private List<ItemViewModel> mList;

    public RecyclerAdapter(Context context, List<ItemViewModel> list) {
        mContext = context;
        mList = list;
    }

    public void setList(List<ItemViewModel> list) {
        mList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListItemBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(mContext) , R.layout.list_item , parent , false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }



    class ViewHolder extends RecyclerView.ViewHolder {
        private ListItemBinding mBinding;

        public ViewHolder(ListItemBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(ItemViewModel viewModel) {
            this.mBinding.setItemViewModel(viewModel);
            mBinding.executePendingBindings();
        }

        public ListItemBinding getBinding() {
            return mBinding;
        }
    }


}
