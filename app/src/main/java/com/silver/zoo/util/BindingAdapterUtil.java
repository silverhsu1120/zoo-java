package com.silver.zoo.util;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class BindingAdapterUtil {

    @BindingAdapter({"image"})
    public static void loadImage(ImageView imageView, int resId) {
        Glide.with(imageView.getContext())
                .load(resId)
                .into(imageView);
    }
}
