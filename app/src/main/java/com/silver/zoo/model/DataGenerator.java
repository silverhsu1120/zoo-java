package com.silver.zoo.model;

import android.content.Context;

import com.silver.zoo.R;

import java.util.ArrayList;
import java.util.List;

public class DataGenerator {

    public static List<House> getHouseList(Context context) {
        List<House> houses = new ArrayList<>();
        houses.add(getInsetHouse(context));
        houses.add(getKoalaHouse(context));
        houses.add(getInsetHouse(context));
        houses.add(getKoalaHouse(context));
        houses.add(getInsetHouse(context));
        houses.add(getKoalaHouse(context));
        houses.add(getInsetHouse(context));
        houses.add(getKoalaHouse(context));
        return houses;
    }

    private static House getInsetHouse(Context context) {
        return new House(
                context.getString(R.string.insect_house),
                context.getString(R.string.msg_insect_house),
                context.getString(R.string.msg_no_closed_info),
                R.drawable.ic_butterfly,
                getPlants(context));
    }

    private static House getKoalaHouse(Context context) {
        return new House(
                context.getString(R.string.koala_house),
                context.getString(R.string.msg_koala_house),
                context.getString(R.string.msg_no_closed_info),
                R.drawable.ic_koala,
                getPlants(context));
    }

    private static List<Plant> getPlants(Context context) {
        List<Plant> plants = new ArrayList<>();
        plants.add(getFireSpike(context));
        plants.add(getFireSpike(context));
        plants.add(getFireSpike(context));
        plants.add(getFireSpike(context));
        plants.add(getFireSpike(context));
        return plants;
    }

    private static Plant getFireSpike(Context context) {
        return new Plant(
                context.getString(R.string.fire_spike),
                context.getString(R.string.msg_fire_spike_alias),
                context.getString(R.string.msg_fire_spike_intro),
                context.getString(R.string.msg_fire_spike_type),
                context.getString(R.string.msg_fire_spike_use),
                R.drawable.ic_fire_spike);
    }
}
