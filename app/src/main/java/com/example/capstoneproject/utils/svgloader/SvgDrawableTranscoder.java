package com.example.capstoneproject.utils.svgloader;

import android.graphics.Picture;
import android.graphics.drawable.PictureDrawable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.resource.SimpleResource;
import com.bumptech.glide.load.resource.transcode.ResourceTranscoder;
import com.caverock.androidsvg.RenderOptions;
import com.caverock.androidsvg.SVG;

public class SvgDrawableTranscoder implements ResourceTranscoder<SVG, PictureDrawable> {

    @Nullable
    @Override
    public Resource<PictureDrawable> transcode(
            @NonNull final Resource<SVG> toTranscode, @NonNull final Options options
    ) {

        final SVG svg = toTranscode.get();
        final RenderOptions renderOptions = RenderOptions.create();
        renderOptions.css(".a,.b,.c { stroke: #FFF; }");

        final Picture picture = svg.renderToPicture(renderOptions);
        final PictureDrawable drawable = new PictureDrawable(picture);

        return new SimpleResource<>(drawable);
    }
}