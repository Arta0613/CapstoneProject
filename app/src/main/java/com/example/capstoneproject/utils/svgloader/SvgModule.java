package com.example.capstoneproject.utils.svgloader;

import android.content.Context;
import android.graphics.drawable.PictureDrawable;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;
import com.caverock.androidsvg.SVG;

import java.io.InputStream;

// Source: https://github.com/bumptech/glide/blob/master/samples/svg/src/main/java/com/bumptech/glide/samples/svg/SvgModule.java
@GlideModule
public class SvgModule extends AppGlideModule {

    @Override
    public void registerComponents(
            @NonNull final Context context, @NonNull final Glide glide, @NonNull final Registry registry
    ) {
        registry
                .register(SVG.class, PictureDrawable.class, new SvgDrawableTranscoder())
                .append(InputStream.class, SVG.class, new SvgDecoder());
    }

    @Override
    public final boolean isManifestParsingEnabled() {
        return false;
    }
}