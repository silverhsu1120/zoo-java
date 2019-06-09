package com.silver.zoo.view.adapter;

import android.arch.lifecycle.LifecycleOwner;
import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.silver.zoo.R;
import com.silver.zoo.databinding.ItemHouseBinding;
import com.silver.zoo.model.House;
import com.silver.zoo.viewmodel.HouseViewModel;

import java.util.ArrayList;
import java.util.List;

public class HouseListAdapter extends RecyclerView.Adapter<HouseListAdapter.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(House house);
    }

    private List<House> houses = new ArrayList<>();
    private OnItemClickListener listener;

    public HouseListAdapter(OnItemClickListener listener) {
        this.listener = listener;
    }

    @BindingAdapter({"houses"})
    public static void load(RecyclerView rv, List<House> houses) {
        if (houses == null) return;
        if (rv.getAdapter() instanceof HouseListAdapter) {
            HouseListAdapter adapter = (HouseListAdapter) rv.getAdapter();
            adapter.houses.clear();
            adapter.houses.addAll(houses);
            adapter.notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemHouseBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_house, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(houses.get(position));
    }

    @Override
    public int getItemCount() {
        return houses.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final ItemHouseBinding binding;

        ViewHolder(final ItemHouseBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(final House house) {
            Context context = binding.getRoot().getContext();
            binding.setViewModel(new HouseViewModel());
            binding.setLifecycleOwner((LifecycleOwner) context);
            binding.getViewModel().getHouse().setValue(house);
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener == null) return;
                    listener.onItemClick(house);
                }
            });
        }
    }
}
