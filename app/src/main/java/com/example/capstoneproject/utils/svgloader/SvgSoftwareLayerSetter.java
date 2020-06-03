package com.example.capstoneproject.utils.svgloader;

import android.graphics.drawable.PictureDrawable;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.ImageViewTarget;
import com.bumptech.glide.request.target.Target;

public class SvgSoftwareLayerSetter implements RequestListener<PictureDrawable> {

    @Override
    public boolean onLoadFailed(
            @Nullable final GlideException e,
            final Object model,
            final Target<PictureDrawable> target,
            final boolean isFirstResource
    ) {
        final ImageView imageView = ((ImageViewTarget<?>) target).getView();
        imageView.setLayerType(ImageView.LAYER_TYPE_NONE, null);

        return false;
    }

    @Override
    public boolean onResourceReady(
            final PictureDrawable resource,
            final Object model,
            final Target<PictureDrawable> target,
            final DataSource dataSource,
            final boolean isFirstResource
    ) {
        final ImageView imageView = ((ImageViewTarget<?>) target).getView();
        imageView.setLayerType(ImageView.LAYER_TYPE_SOFTWARE, null);

        return false;
    }
}