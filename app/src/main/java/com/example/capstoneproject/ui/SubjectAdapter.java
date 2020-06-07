package com.example.capstoneproject.ui;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.example.capstoneproject.BR;
import com.example.capstoneproject.R;
import com.example.capstoneproject.domain.SubjectType;
import com.example.capstoneproject.utils.SingleLiveEvent;

import java.util.ArrayList;
import java.util.List;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.SubjectViewHolder> {

    @NonNull private List<SubjectType> subjectList = new ArrayList<>();
    private SingleLiveEvent<SubjectType> showDetail;

    @NonNull
    @Override
    public SubjectViewHolder onCreateViewHolder(
            @NonNull final ViewGroup parent, final int viewType
    ) {
        final ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_subject, parent, false);

        return new SubjectViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull final SubjectViewHolder holder, final int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return subjectList.size();
    }

    public void setSubjectList(@NonNull final List<SubjectType> subjectList) {
        this.subjectList = subjectList;
    }

    public void setShowDetailEvent(@NonNull final SingleLiveEvent<SubjectType> showDetail) {
        this.showDetail = showDetail;
    }

    class SubjectViewHolder extends RecyclerView.ViewHolder {

        private final ViewDataBinding binding;

        public SubjectViewHolder(@NonNull final ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(final int position) {
            binding.getRoot().setOnClickListener(l -> showDetail.setValue(subjectList.get(position)));
            binding.setVariable(BR.subjectType, subjectList.get(position));
            binding.executePendingBindings();
        }
    }
}