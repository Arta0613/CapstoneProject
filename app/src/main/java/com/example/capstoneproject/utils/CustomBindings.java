package com.example.capstoneproject.utils;

import android.graphics.drawable.PictureDrawable;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.capstoneproject.utils.svgloader.GlideApp;
import com.example.capstoneproject.utils.svgloader.SvgSoftwareLayerSetter;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class CustomBindings {

    @NonNull
    private static final SvgSoftwareLayerSetter svgSoftwareLayerSetter = new SvgSoftwareLayerSetter();

    @BindingAdapter("setAdapter")
    public static void bindRecyclerViewAdapter(
            @NonNull final RecyclerView recyclerView, final RecyclerView.Adapter<?> adapter
    ) {
        recyclerView.setLayoutManager(
                new GridLayoutManager(recyclerView.getContext(), 2, RecyclerView.HORIZONTAL, false)
        );
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setClipToPadding(false);
        recyclerView.setAdapter(adapter);
    }

    @BindingAdapter("cardBackground")
    public static void bindCardBackgroundColor(
            @NonNull final CardView cardView, @ColorRes final int color
    ) {
        cardView.setCardBackgroundColor(ContextCompat.getColor(cardView.getContext(), color));
    }

    @BindingAdapter("imageUrl")
    public static void bindImageUrl(
            @NonNull final ImageView imageView, @NonNull final String imageUrl
    ) {
        GlideApp.with(imageView.getContext()).as(PictureDrawable.class).transition(withCrossFade()).listener(svgSoftwareLayerSetter).load(imageUrl).into(imageView);
    }

    @BindingAdapter("visibleOrGone")
    public static void setVisibleOrGone(final View view, final Boolean visible) {
        if (visible != null) {
            view.setVisibility(visible ? View.VISIBLE : View.GONE);
        }
    }

    @BindingAdapter("listItemAutoSize")
    public static void setListItemTextSize(
            final AppCompatTextView textView, final int charactersLength
    ) {
        switch (charactersLength) {
            case 1:
            case 2:
                textView.setTextSize(100);
                break;
            case 3:
                textView.setTextSize(80);
                break;
            case 4:
                textView.setTextSize(55);
                break;
            case 5:
                textView.setTextSize(50);
                break;
            default:
                textView.setTextSize(35);
        }
    }

    @BindingAdapter("detailAutoSize")
    public static void setDetailTextSize(
            final AppCompatTextView textView, final int charactersLength
    ) {
        switch (charactersLength) {
            case 1:
            case 2:
                textView.setTextSize(115);
                break;
            case 3:
                textView.setTextSize(100);
                break;
            case 4:
                textView.setTextSize(90);
                break;
            default:
                textView.setTextSize(80);
        }
    }

    @BindingAdapter("spannedText")
    public static void setSpannedText(
            @NonNull final AppCompatTextView textView, final String mnemonic
    ) {
        textView.setText(SubjectHelper.getColorCodedMnemonic(textView.getContext(), mnemonic));
    }
}