package com.silver.zoo.view.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.silver.zoo.R;
import com.silver.zoo.databinding.ActivityMainBinding;
import com.silver.zoo.model.House;
import com.silver.zoo.model.Plant;
import com.silver.zoo.view.fragment.HouseInfoFragment;
import com.silver.zoo.view.fragment.HouseListFragment;
import com.silver.zoo.view.fragment.PlantInfoFragment;
import com.silver.zoo.viewmodel.NavBarViewModel;

public class MainActivity extends AppCompatActivity {

    private static final String TAG_HOUSE_LIST = HouseListFragment.class.getSimpleName();
    private static final String TAG_HOUSE_INFO = HouseInfoFragment.class.getSimpleName();
    private static final String TAG_PLANT_INFO = PlantInfoFragment.class.getSimpleName();

    private ActivityMainBinding binding;
    private NavBarViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initView();
        initViewModel();
    }

    private void initView() {
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processBackEvent();
            }
        });
    }

    private void initViewModel() {
        viewModel = ViewModelProviders.of(this).get(NavBarViewModel.class);
        viewModel.getState().observe(this, new Observer<NavBarViewModel.State>() {
            @Override
            public void onChanged(@Nullable NavBarViewModel.State state) {
                if (state == null) return;
                binding.toolbar.setNavigationIcon(state.getIcon());
                binding.tvTitle.setText(state.getTitle());
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        displayHouseList();
    }

    @Override
    public void onBackPressed() {
        processBackEvent();
    }

    private void displayHouseList() {
        viewModel.pushState(R.drawable.ic_menu_black, getString(R.string.app_name));
        addFragment(HouseListFragment.newInstance(), TAG_HOUSE_LIST);
    }

    public void displayHouseInfo(House house) {
        viewModel.pushState(R.drawable.ic_arrow_back_black, house.getName());
        addFragment(HouseInfoFragment.newInstance(house), TAG_HOUSE_INFO);
    }

    public void displayPlantInfo(Plant plant) {
        viewModel.pushState(R.drawable.ic_arrow_back_black, plant.getName());
        addFragment(PlantInfoFragment.newInstance(plant), TAG_PLANT_INFO);
    }

    private void processBackEvent() {
        switch (viewModel.getStack().size()) {
            case 3:
                viewModel.popState();
                removeFragment(getSupportFragmentManager().findFragmentByTag(TAG_PLANT_INFO));
                break;
            case 2:
                viewModel.popState();
                removeFragment(getSupportFragmentManager().findFragmentByTag(TAG_HOUSE_INFO));
                break;
            default:
                break;
        }
    }

    private void addFragment(Fragment f, String tag) {
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .add(R.id.fl_container, f, tag)
                .commitAllowingStateLoss();
    }

    private void removeFragment(Fragment f) {
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .remove(f)
                .commitAllowingStateLoss();
    }
}
