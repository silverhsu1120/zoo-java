package com.silver.zoo.util;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.silver.zoo.R;

public class BindingAdapterUtil {

    @BindingAdapter({"image"})
    public static void loadImage(ImageView imageView, String picUrl) {
        Glide.with(imageView.getContext())
                .load(picUrl)
                .apply(new RequestOptions().error(R.mipmap.ic_launcher))
                .into(imageView);
    }
}
