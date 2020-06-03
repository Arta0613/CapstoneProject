package com.example.capstoneproject.utils.svgloader;

import androidx.annotation.NonNull;

import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.resource.SimpleResource;
import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGParseException;

import java.io.IOException;
import java.io.InputStream;

import static com.bumptech.glide.request.target.Target.SIZE_ORIGINAL;

public class SvgDecoder implements ResourceDecoder<InputStream, SVG> {

    @Override
    public final boolean handles(
            @NonNull final InputStream source, @NonNull final Options options
    ) {
        return true;
    }

    @NonNull
    @Override
    public final Resource<SVG> decode(
            @NonNull final InputStream source,
            final int width,
            final int height,
            @NonNull final Options options
    ) throws IOException {

        try {
            final SVG svg = SVG.getFromInputStream(source);

            if (width != SIZE_ORIGINAL) {
                svg.setDocumentWidth((float) width);
            }

            if (height != SIZE_ORIGINAL) {
                svg.setDocumentHeight((float) height);
            }

            return new SimpleResource<>(svg);
        } catch (SVGParseException e) {
            throw new IOException("Cannot load SVG from stream: ", e);
        }
    }
}