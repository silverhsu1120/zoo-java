package com.silver.zoo.view.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.silver.zoo.R;
import com.silver.zoo.databinding.FragmentHouseInfoBinding;
import com.silver.zoo.model.House;
import com.silver.zoo.model.Plant;
import com.silver.zoo.view.activity.MainActivity;
import com.silver.zoo.view.adapter.HouseInfoAdapter;
import com.silver.zoo.viewmodel.HouseViewModel;

import java.util.Objects;


public class HouseInfoFragment extends Fragment implements HouseInfoAdapter.OnItemClickListener {

    private static final String ARG_HOUSE = "house";

    private FragmentHouseInfoBinding binding;
    private House house;

    public HouseInfoFragment() {

    }

    public static HouseInfoFragment newInstance(House house) {
        HouseInfoFragment fragment = new HouseInfoFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_HOUSE, house);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            house = (House) getArguments().getSerializable(ARG_HOUSE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_house_info, container, false);
        initView();
        initViewModel();
        return binding.getRoot();
    }

    private FragmentActivity getActivityNonNull() {
        return Objects.requireNonNull(getActivity());
    }

    private void initView() {
        binding.rvHouseInfo.setAdapter(new HouseInfoAdapter(house, this));
        binding.rvHouseInfo.setLayoutManager(new LinearLayoutManager(getActivityNonNull()));
        binding.rvHouseInfo.addItemDecoration(new DividerItemDecoration(getActivityNonNull(), DividerItemDecoration.VERTICAL));
    }

    private void initViewModel() {
        binding.setViewModel(ViewModelProviders.of(getActivityNonNull()).get(HouseViewModel.class));
        binding.setLifecycleOwner(this);
        binding.getViewModel().getHouse().observe(this, new Observer<House>() {
            @Override
            public void onChanged(@Nullable House house) {
                if (binding.rvHouseInfo.getAdapter() instanceof HouseInfoAdapter) {
                    HouseInfoAdapter adapter = (HouseInfoAdapter) binding.rvHouseInfo.getAdapter();
                    adapter.load(house);
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        binding.getViewModel().getHouse().setValue(house);
    }

    @Override
    public void onItemClick(Plant plant) {
        if (getActivityNonNull() instanceof MainActivity) {
            ((MainActivity) getActivityNonNull()).displayPlantInfo(plant);
        }
    }
}
