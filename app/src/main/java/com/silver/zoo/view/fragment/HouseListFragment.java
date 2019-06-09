package com.silver.zoo.view.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.silver.zoo.R;
import com.silver.zoo.databinding.FragmentHouseListBinding;
import com.silver.zoo.model.DataGenerator;
import com.silver.zoo.model.House;
import com.silver.zoo.view.activity.MainActivity;
import com.silver.zoo.view.adapter.HouseListAdapter;
import com.silver.zoo.viewmodel.HouseListViewModel;

import java.util.Objects;

public class HouseListFragment extends Fragment implements HouseListAdapter.OnItemClickListener {

    private FragmentHouseListBinding binding;

    public HouseListFragment() {

    }

    public static HouseListFragment newInstance() {
        return new HouseListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_house_list, container, false);
        initView();
        initViewModel();
        return binding.getRoot();
    }

    private FragmentActivity getActivityNonNull() {
        return Objects.requireNonNull(getActivity());
    }

    private void initView() {
        binding.rvHouse.setAdapter(new HouseListAdapter(this));
        binding.rvHouse.setLayoutManager(new LinearLayoutManager(getActivityNonNull()));
        binding.rvHouse.addItemDecoration(new DividerItemDecoration(getActivityNonNull(), DividerItemDecoration.VERTICAL));
    }

    private void initViewModel() {
        HouseListViewModel viewModel = ViewModelProviders.of(getActivityNonNull()).get(HouseListViewModel.class);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        binding.getViewModel().getHouses().setValue(DataGenerator.getHouseList(this.getContext()));
    }

    @Override
    public void onItemClick(House house) {
        if (getActivityNonNull() instanceof MainActivity) {
            ((MainActivity) getActivityNonNull()).displayHouseInfo(house);
        }
    }
}
