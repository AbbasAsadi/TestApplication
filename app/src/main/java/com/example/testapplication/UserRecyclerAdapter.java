package com.example.testapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testapplication.databinding.ListItemUserListBinding;
import com.example.testapplication.viewModel.UserItemViewModel;

import java.util.ArrayList;
import java.util.List;

public class UserRecyclerAdapter extends RecyclerView.Adapter<UserRecyclerAdapter.RecyclerViewHolder> {
    private Context mContext;
    private List<UserItemViewModel> mUserList = new ArrayList<>();

    public UserRecyclerAdapter(Context context, List<UserItemViewModel> userList) {
        mContext = context;
        mUserList = userList;
    }

    public void setUserList(List<UserItemViewModel> userList) {
        mUserList = userList;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListItemUserListBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(mContext),
                        R.layout.list_item_user_list,
                        parent,
                        false);

        return new RecyclerViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        holder.bind(mUserList.get(position));
    }

    @Override
    public int getItemCount() {
        return mUserList.size();
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private ListItemUserListBinding mBinding;

        RecyclerViewHolder(ListItemUserListBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        void bind(UserItemViewModel viewModel) {
            this.mBinding.setViewModel(viewModel);
            mBinding.executePendingBindings();
        }

    }
}

