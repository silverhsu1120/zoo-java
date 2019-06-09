package com.silver.zoo.view.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.silver.zoo.R;
import com.silver.zoo.databinding.FragmentPlantInfoBinding;
import com.silver.zoo.model.Plant;
import com.silver.zoo.viewmodel.PlantViewModel;

import java.util.Objects;

public class PlantInfoFragment extends Fragment {

    private static final String ARG_PLANT = "plant";

    private FragmentPlantInfoBinding binding;
    private Plant plant;

    public PlantInfoFragment() {

    }

    public static PlantInfoFragment newInstance(Plant plant) {
        PlantInfoFragment fragment = new PlantInfoFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PLANT, plant);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            plant = (Plant) getArguments().getSerializable(ARG_PLANT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_plant_info, container, false);
        initViewModel();
        return binding.getRoot();
    }

    private FragmentActivity getActivityNonNull() {
        return Objects.requireNonNull(getActivity());
    }

    private void initViewModel() {
        binding.setViewModel(ViewModelProviders.of(getActivityNonNull()).get(PlantViewModel.class));
        binding.setLifecycleOwner(this);
        binding.getViewModel().getPlant().setValue(plant);
    }
}
