package com.silver.zoo.view.adapter;

import android.arch.lifecycle.LifecycleOwner;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.silver.zoo.R;
import com.silver.zoo.databinding.ItemHouseInfoBinding;
import com.silver.zoo.databinding.ItemPlantBinding;
import com.silver.zoo.model.House;
import com.silver.zoo.model.Plant;
import com.silver.zoo.viewmodel.HouseViewModel;
import com.silver.zoo.viewmodel.PlantViewModel;

public class HouseInfoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_NORMAL = 0;
    private static final int TYPE_HEADER = 1;

    public interface OnItemClickListener {
        void onItemClick(Plant plant);
    }

    private House house;
    private OnItemClickListener listener;

    public HouseInfoAdapter(House house, OnItemClickListener listener) {
        this.house = house;
        this.listener = listener;
    }

    public void load(House house) {
        this.house = house;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                (viewType == TYPE_HEADER) ? R.layout.item_house_info : R.layout.item_plant,
                parent, false);

        return (viewType == TYPE_HEADER)
                ? new HeaderHolder((ItemHouseInfoBinding) binding)
                : new ViewHolder((ItemPlantBinding) binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeaderHolder) {
            ((HeaderHolder) holder).bind(house);
        } else {
            ((ViewHolder) holder).bind(house.getPlants().get(position - 1));
        }
    }

    @Override
    public int getItemCount() {
        return house.getPlants().size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        return (position == 0) ? TYPE_HEADER : TYPE_NORMAL;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final ItemPlantBinding binding;

        ViewHolder(ItemPlantBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(final Plant plant) {
            Context context = binding.getRoot().getContext();
            PlantViewModel viewModel = new PlantViewModel();
            binding.setViewModel(viewModel);
            binding.setLifecycleOwner((LifecycleOwner) context);
            binding.getViewModel().getPlant().setValue(plant);
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener == null) return;
                    listener.onItemClick(plant);
                }
            });
        }
    }

    class HeaderHolder extends RecyclerView.ViewHolder {

        private final ItemHouseInfoBinding binding;

        HeaderHolder(ItemHouseInfoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(final House house) {
            Context context = binding.getRoot().getContext();
            binding.setViewModel(new HouseViewModel());
            binding.setLifecycleOwner((LifecycleOwner) context);
            binding.getViewModel().getHouse().setValue(house);
        }
    }
}
