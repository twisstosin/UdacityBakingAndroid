package com.twisstosin.udacitybaking.Adapters;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.twisstosin.udacitybaking.DataModel.Step;
import com.twisstosin.udacitybaking.R;
import com.twisstosin.udacitybaking.Utils.OnItemClickListener;
import com.twisstosin.udacitybaking.databinding.StepListItemBinding;

import java.util.ArrayList;

public class StepAdapter extends RecyclerView.Adapter<StepAdapter.StepHolder> {
    private ArrayList<Step> mStepList;
    private OnItemClickListener mStepOnItemClickListener;
    private int mSelectedPosition = 0;

    public StepAdapter(ArrayList<Step> incomingStepSet,
                       OnItemClickListener<Step> StepOnItemClickListener) {
        this.mStepList = incomingStepSet;
        this.mStepOnItemClickListener = StepOnItemClickListener;
    }

    @Override
    public StepHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        StepListItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.step_list_item, parent, false);
        return new StepHolder(binding);
    }

    @Override
    public void onBindViewHolder(StepHolder holder, int position) {
        holder.mStep = mStepList.get(position);
        holder.mBinding.cvStepItemHolder.setSelected(mSelectedPosition == position);

        if(holder.mStep.getVideoURL()!=null && !holder.mStep.getVideoURL().matches("")) {
            Glide.with(holder.itemView.getContext())
                    .load(holder.mStep.getThumbnailURL())
                    .placeholder(R.drawable.ic_videocam_black_24dp)
                    .error(R.drawable.ic_videocam_black_24dp)
                    .dontAnimate()
                    .into(holder.mBinding.ivStepItemVideoThumb);
        } else {
            holder.mBinding.ivStepItemVideoThumb.setImageResource(R.drawable.ic_videocam_off_black_24dp);
        }
        if(holder.mStep.getId() == 0)
            holder.mBinding.tvStepListStepNumber.setText("   ");
        else
        holder.mBinding.tvStepListStepNumber.setText(String.valueOf(holder.mStep.getId()) + ": ");
        holder.mBinding.tvStepListStepShortDesc.setText(holder.mStep.getShortDescription());
    }

    @Override
    public int getItemCount() {
        return (mStepList == null) ? 0 : mStepList.size();
    }

    public class StepHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final StepListItemBinding mBinding;
        private Step mStep;

        public StepHolder(StepListItemBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;

            binding.cvStepItemHolder.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            notifyItemChanged(mSelectedPosition);
            mSelectedPosition = getLayoutPosition();
            notifyItemChanged(mSelectedPosition);
            mStepOnItemClickListener.onClick(mStep, v);
        }
    }
    public int getStepAdapterCurrentPosition(){
        return mSelectedPosition;
    }
    public void setStepAdapterCurrentPosition(int savedPosition){
        mSelectedPosition = savedPosition;
    }
}
